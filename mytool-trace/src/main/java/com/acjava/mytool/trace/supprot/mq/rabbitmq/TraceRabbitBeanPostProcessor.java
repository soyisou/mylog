package com.acjava.mytool.trace.supprot.mq.rabbitmq;

import com.acjava.mytool.trace.util.ReflectUtil;
import org.aopalliance.aop.Advice;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.config.AbstractRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import java.lang.reflect.Field;

/**
 * Rabbitmq相关的后处理器
 *
 * @author loujm
 */
public class TraceRabbitBeanPostProcessor implements BeanPostProcessor, BeanFactoryAware {

    private BeanFactory beanFactory;
    final Field beforePublishPostProcessorsField;
    final Field beforeSendReplyPostProcessorsField;

    {
        beforePublishPostProcessorsField = ReflectUtil
                .getField(RabbitTemplate.class, "beforePublishPostProcessors");
        beforeSendReplyPostProcessorsField = ReflectUtil
                .getField(AbstractRabbitListenerContainerFactory.class, "beforeSendReplyPostProcessors");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof RabbitTemplate) {
            return decorateRabbitTemplate((RabbitTemplate) bean);
        } else if (bean instanceof SimpleRabbitListenerContainerFactory) {
            return decorateSimpleRabbitListenerContainerFactory((SimpleRabbitListenerContainerFactory) bean);
        }
        return bean;
    }

    /** 生产者：添加发送消息后处理器 */
    private RabbitTemplate decorateRabbitTemplate(RabbitTemplate rabbitTemplate) {
        MessagePostProcessor[] beforePublishPostProcessors =
                appendMessagePostProcessor(
                        rabbitTemplate,
                        beforePublishPostProcessorsField,
                        new AddTraceProcessor());

        if (beforePublishPostProcessors != null)
            rabbitTemplate.setBeforePublishPostProcessors(beforePublishPostProcessors);
        return rabbitTemplate;
    }

    /** 消费者：消费前处理 + 消费后处理 */
    private SimpleRabbitListenerContainerFactory decorateSimpleRabbitListenerContainerFactory(SimpleRabbitListenerContainerFactory factory) {
        // 添加环绕通知：消费消息前设置MDC，消费结束清除MDC
        Advice[] advice = appendTraceRabbitListenerAdvice(factory);
        if (advice != null) factory.setAdviceChain(advice);

        // requeue MQ时，需要回退span信息
        MessagePostProcessor[] beforeSendReplyPostProcessors =
                appendMessagePostProcessor(factory, beforeSendReplyPostProcessorsField, new FallBackTraceProcessor());
        if (beforeSendReplyPostProcessors != null) factory.setBeforeSendReplyPostProcessors(beforeSendReplyPostProcessors);

        return factory;
    }

    /** 如果 无法添加 或 无需添加，则返回null */
    private Advice[] appendTraceRabbitListenerAdvice(SimpleRabbitListenerContainerFactory factory) {
        Advice[] chain = factory.getAdviceChain();
        TraceRabbitListenerAdvice traceAdvice = new TraceRabbitListenerAdvice();
        if (chain == null) return new Advice[] {traceAdvice};

        for (Advice advice : chain) {
            // 无需添加
            if (advice instanceof TraceRabbitListenerAdvice) return null;
        }

        Advice[] res = new Advice[chain.length + 1];
        res[0] = traceAdvice;
        System.arraycopy(chain, 0, res, 1, chain.length);
        return res;
    }

    /** 如果 无法添加 或 无需添加，则返回null */
    private MessagePostProcessor[] appendMessagePostProcessor(Object object,
                                                              Field field,
                                                              TraceMessagePostProcessor traceProcessor) {
        // 无法添加
        if (field == null) return null;

        MessagePostProcessor[] processors;
        try {
            processors = (MessagePostProcessor[]) field.get(object);
        } catch (Exception e) {
            // 无法添加
            return null;
        }
        if (processors == null || processors.length == 0)
            return new MessagePostProcessor[] {traceProcessor};
        for (MessagePostProcessor processor : processors) {
            // 无需添加
            if (processor instanceof TraceMessagePostProcessor) return null;
        }
        MessagePostProcessor[] res = new MessagePostProcessor[processors.length + 1];
        System.arraycopy(processors, 0, res, 0, processors.length);
        res[processors.length] = traceProcessor;
        return res;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}

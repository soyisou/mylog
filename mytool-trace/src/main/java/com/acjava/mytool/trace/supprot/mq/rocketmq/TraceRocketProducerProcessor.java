package com.acjava.mytool.trace.supprot.mq.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author loujm
 */
public class TraceRocketProducerProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DefaultMQProducer) {
            DefaultMQProducer defaultMQProducer = (DefaultMQProducer) bean;
            defaultMQProducer.getDefaultMQProducerImpl().registerSendMessageHook(new TraceSendMessageHook());
        }
        return bean;
    }

}

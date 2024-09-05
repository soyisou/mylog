package com.acjava.mytool.autoconfigure;

import com.acjava.mytool.trace.supprot.mq.rabbitmq.TraceRabbitBeanPostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 链路追踪RabbitMQ的自动配置类
 *
 * @author loujm
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "mytool.trace.mq.rabbit", name = "enabled",
        havingValue = "true", matchIfMissing = true)
@ConditionalOnClass(RabbitTemplate.class)
public class TraceRabbitAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public TraceRabbitBeanPostProcessor traceRabbitBeanPostProcessor() {
        return new TraceRabbitBeanPostProcessor();
    }
}

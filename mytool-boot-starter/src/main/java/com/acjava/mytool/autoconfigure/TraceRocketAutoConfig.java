package com.acjava.mytool.autoconfigure;

import com.acjava.mytool.trace.supprot.mq.rocketmq.TraceRocketConsumerListener;
import com.acjava.mytool.trace.supprot.mq.rocketmq.TraceRocketProducerProcessor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 链路追踪RocketMQ的自动配置类
 *
 * @author loujm
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "mytool.trace.mq.rocket", name = "enabled",
        havingValue = "true", matchIfMissing = true)
@ConditionalOnClass(RocketMQTemplate.class)
public class TraceRocketAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public TraceRocketProducerProcessor traceRocketBeanPostProcessor() {
        return new TraceRocketProducerProcessor();
    }

    @Bean
    @ConditionalOnMissingBean
    public TraceRocketConsumerListener traceRocketConsumerListener() {
        return new TraceRocketConsumerListener();
    }
}

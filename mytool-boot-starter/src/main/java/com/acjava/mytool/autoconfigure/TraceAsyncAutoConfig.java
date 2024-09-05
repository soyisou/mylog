package com.acjava.mytool.autoconfigure;

import com.acjava.mytool.trace.supprot.async.TraceExecutorBeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author loujm
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "mytool.trace.async", name = "enabled",
        havingValue = "true", matchIfMissing = true)
public class TraceAsyncAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public TraceExecutorBeanPostProcessor traceExecutorBeanPostProcessor() {
        return new TraceExecutorBeanPostProcessor();
    }
}

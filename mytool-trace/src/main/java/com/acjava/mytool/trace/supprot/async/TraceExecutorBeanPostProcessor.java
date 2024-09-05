package com.acjava.mytool.trace.supprot.async;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author loujm
 */
public class TraceExecutorBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TraceThreadPoolTaskExecutor) {
            return bean;
        }
        // 直接返回bean的，表示暂未支持链路追踪
        if (bean instanceof ThreadPoolTaskExecutor) {
            return wrapThreadPoolTaskExecutor(bean);
        } else if (bean instanceof ScheduledExecutorService) {
            return bean;
        } else if (bean instanceof ExecutorService) {
            return bean;
        } else if (bean instanceof AsyncTaskExecutor) {
            return bean;
        } else if (bean instanceof Executor) {
            return bean;
        }
        return bean;
    }

    private Object wrapThreadPoolTaskExecutor(Object bean) {
        ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) bean;
        return new TraceThreadPoolTaskExecutor(executor);
    }

}

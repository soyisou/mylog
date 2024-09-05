package com.acjava.mytool.autoconfigure;

import com.acjava.mytool.annotation.async.aspect.MyAsyncAspect;
import com.acjava.mytool.annotation.async.pool.MyAsyncDefaultPool;
import com.acjava.mytool.core.util.spring.SpringUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: @MyAsync自动配置类
 * @author: loujm
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({DefaultPoolProperties.class})
@ConditionalOnProperty(prefix = "mytool.my-async", name = "enabled", havingValue = "true", matchIfMissing = true)
public class MyAsyncAutoConfig {

    private DefaultPoolProperties defaultPoolProperties;

    public MyAsyncAutoConfig(DefaultPoolProperties defaultPoolProperties) {
        this.defaultPoolProperties = defaultPoolProperties;
    }

    @Bean("defaultPool")
    @ConditionalOnProperty(prefix = "mytool.my-async", name = "default-pool", havingValue = "true", matchIfMissing = true)
    @ConditionalOnMissingBean(MyAsyncDefaultPool.class)
    public Executor defaultPool() {
        MyAsyncDefaultPool executor = new MyAsyncDefaultPool();
        // 核心线程数
        executor.setCorePoolSize(defaultPoolProperties.getCorePoolSize());
        // 最大线程数
        executor.setMaxPoolSize(defaultPoolProperties.getMaxPoolSize());
        // 允许线程的空闲时间
        executor.setKeepAliveSeconds(defaultPoolProperties.getKeepAliveSeconds());
        // 任务队列的大小
        executor.setQueueCapacity(defaultPoolProperties.getQueueCapacity());
        // 设置线程池中任务的等待时间。如果超过这个时间还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        executor.setAwaitTerminationSeconds(defaultPoolProperties.getAwaitTerminationSeconds());
        // 线程名前缀
        executor.setThreadNamePrefix(defaultPoolProperties.getThreadNamePrefix());
        // 设置线程池关闭的时候等待所有任务都完成，再继续销毁其他的Bean
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 设置拒绝策略为：直接抛出异常
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 初始化线程
        executor.initialize();
        return executor;
    }

    @Bean
    @ConditionalOnMissingBean
    public SpringUtil contextUtil() {
        return new SpringUtil();
    }


    @Bean
    @ConditionalOnMissingBean
    public MyAsyncAspect myAsyncAspect() {
        return new MyAsyncAspect();
    }
}

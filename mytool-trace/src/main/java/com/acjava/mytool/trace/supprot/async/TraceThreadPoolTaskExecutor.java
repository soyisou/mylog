package com.acjava.mytool.trace.supprot.async;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.*;

/**
 * @author loujm
 */
public class TraceThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    private final ThreadPoolTaskExecutor delegate;

    public TraceThreadPoolTaskExecutor(ThreadPoolTaskExecutor delegate) {
        this.delegate = delegate;
    }

    public String getTraceId() {
        return MDC.get("traceId");
    }

    @Override
    public void execute(Runnable task) {
        this.delegate.execute(new TraceRunnable(task, getTraceId()));
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        this.delegate.execute(new TraceRunnable(task, getTraceId()), startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return this.delegate.submit(new TraceRunnable(task, getTraceId()));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return this.delegate.submit(new TraceCallable<>(task, getTraceId()));
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        return this.delegate.submitListenable(new TraceRunnable(task, getTraceId()));
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        return this.delegate.submitListenable(new TraceCallable<>(task, getTraceId()));
    }

    @Override
    public boolean prefersShortLivedTasks() {
        return this.delegate.prefersShortLivedTasks();
    }

    @Override
    public void setThreadFactory(ThreadFactory threadFactory) {
        this.delegate.setThreadFactory(threadFactory);
    }

    @Override
    public void setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
        this.delegate.setRejectedExecutionHandler(rejectedExecutionHandler);
    }

    @Override
    public void setWaitForTasksToCompleteOnShutdown(boolean waitForJobsToCompleteOnShutdown) {
        this.delegate.setWaitForTasksToCompleteOnShutdown(waitForJobsToCompleteOnShutdown);
    }

    @Override
    public void setAwaitTerminationSeconds(int awaitTerminationSeconds) {
        this.delegate.setAwaitTerminationSeconds(awaitTerminationSeconds);
    }

    @Override
    public void setBeanName(String name) {
        this.delegate.setBeanName(name);
    }

    @Override
    public ThreadPoolExecutor getThreadPoolExecutor() throws IllegalStateException {
        return this.delegate.getThreadPoolExecutor();
    }

    @Override
    public int getPoolSize() {
        return this.delegate.getPoolSize();
    }

    @Override
    public int getActiveCount() {
        return this.delegate.getActiveCount();
    }

    @Override
    public void destroy() {
        this.delegate.destroy();
        super.destroy();
    }

    @Override
    public void afterPropertiesSet() {
        this.delegate.afterPropertiesSet();
        super.afterPropertiesSet();
    }

    @Override
    public void initialize() {
        this.delegate.initialize();
    }

    @Override
    public void shutdown() {
        this.delegate.shutdown();
        super.shutdown();
    }

    @Override
    public Thread newThread(Runnable runnable) {
        return this.delegate.newThread(runnable);
    }

    @Override
    public String getThreadNamePrefix() {
        return this.delegate.getThreadNamePrefix();
    }

    @Override
    public void setThreadNamePrefix(String threadNamePrefix) {
        this.delegate.setThreadNamePrefix(threadNamePrefix);
    }

    @Override
    public int getThreadPriority() {
        return this.delegate.getThreadPriority();
    }

    @Override
    public void setThreadPriority(int threadPriority) {
        this.delegate.setThreadPriority(threadPriority);
    }

    @Override
    public boolean isDaemon() {
        return this.delegate.isDaemon();
    }

    @Override
    public void setDaemon(boolean daemon) {
        this.delegate.setDaemon(daemon);
    }

    @Override
    public void setThreadGroupName(String name) {
        this.delegate.setThreadGroupName(name);
    }

    @Override
    public ThreadGroup getThreadGroup() {
        return this.delegate.getThreadGroup();
    }

    @Override
    public void setThreadGroup(ThreadGroup threadGroup) {
        this.delegate.setThreadGroup(threadGroup);
    }

    @Override
    public Thread createThread(Runnable runnable) {
        return this.delegate.createThread(runnable);
    }

    @Override
    public int getCorePoolSize() {
        return this.delegate.getCorePoolSize();
    }

    @Override
    public void setCorePoolSize(int corePoolSize) {
        this.delegate.setCorePoolSize(corePoolSize);
    }

    @Override
    public int getMaxPoolSize() {
        return this.delegate.getMaxPoolSize();
    }

    @Override
    public void setMaxPoolSize(int maxPoolSize) {
        this.delegate.setMaxPoolSize(maxPoolSize);
    }

    @Override
    public int getKeepAliveSeconds() {
        return this.delegate.getKeepAliveSeconds();
    }

    @Override
    public void setKeepAliveSeconds(int keepAliveSeconds) {
        this.delegate.setKeepAliveSeconds(keepAliveSeconds);
    }

    @Override
    public void setQueueCapacity(int queueCapacity) {
        this.delegate.setQueueCapacity(queueCapacity);
    }

    @Override
    public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        this.delegate.setAllowCoreThreadTimeOut(allowCoreThreadTimeOut);
    }

    @Override
    public void setTaskDecorator(TaskDecorator taskDecorator) {
        this.delegate.setTaskDecorator(taskDecorator);
    }


}

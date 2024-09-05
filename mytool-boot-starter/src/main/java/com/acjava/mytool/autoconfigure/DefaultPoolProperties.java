package com.acjava.mytool.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: 默认线程池的配置参数
 * @author: loujm
 */

@Data
@ConfigurationProperties(prefix = "mytool.my-async.default-pool")
public class DefaultPoolProperties {

    /** cpu的核心数 */
    private final int cpuCore = Runtime.getRuntime().availableProcessors();
    /** 核心线程数 */
    private int corePoolSize = cpuCore * 2;
    /** 最大线程数 */
    private int maxPoolSize = cpuCore * 4;
    /** 允许线程的空闲时间 */
    private int keepAliveSeconds = 180;
    /** 任务队列的大小 */
    private int queueCapacity = 500;
    /** 设置线程池中任务的等待时间。如果超过这个时间还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住 */
    private int awaitTerminationSeconds = 60;
    /** 线程名前缀 */
    private String threadNamePrefix = "mytool-default-pool-";
}

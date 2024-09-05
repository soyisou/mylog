package com.acjava.mytool.annotation.log.bean;

import com.acjava.mytool.annotation.log.enums.MethodType;
import lombok.Builder;
import lombok.Data;

/**
 * @Description: 统一日志Bean
 * @author: loujm
 * @date: 2023/7/11
 */
@Data
@Builder
public class MyLogBean {

    // -----------------目标方法运行前可以确定的属性

    /** 名称 */
    private String name;

    /** 调用类型 */
    private MethodType methodType;

    /** 执行线程 */
    private String thread;

    /** 当前时间，精确到ms */
    private String time;

    /** 应用名称 */
    private String app;

    /** 运行环境 */
    private String env;

    /** 链路追踪ID */
    private String traceId;

    /** spanId */
    private String spanId;

    /** 用户ID */
    private String userId;

    /** 来源ip */
    private String remoteIp;

    /** http返回Code */
    private String httpCode;

    // -----------------目标方法运行后才确定的属性

    /** 入参 */
    private Object param;

    /** 出参 */
    private Object result;

    /** 耗时，ms */
    private long rt;

    /** true=成功 */
    private boolean success;

    /** 结果集大小 */
    private int resultSize = 0;

}

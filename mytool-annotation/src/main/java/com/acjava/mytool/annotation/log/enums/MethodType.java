package com.acjava.mytool.annotation.log.enums;

/**
 * 调用方法类型
 */
public enum MethodType {
    DEFAULT("default", "默认"),
    HTTP_UP("http_up", "上游调用http"),
    HTTP_DOWN("http_down", "调用下游http"),
    FEIGN_UP("feign_up", "上游调用feign"),
    FEIGN_DOWN("feign_down", "调用下游feign"),
    SERVICE("service", "内部服务"),
    MQ("mq", "mq"),
    DB("db", "db"),
    ASYNC("async", "异步任务"),
    XXL_JOB("xxl_job", "定时任务"),
    ;

    private String value;
    private String desc;

    MethodType(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

}

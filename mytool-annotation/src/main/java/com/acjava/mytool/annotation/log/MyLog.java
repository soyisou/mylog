package com.acjava.mytool.annotation.log;

import com.acjava.mytool.annotation.log.enums.MethodType;
import com.acjava.mytool.core.util.StringUtil;

import java.lang.annotation.*;

/**
 * @Description: 统一日志处理注解
 * @author: loujm
 * @date: 2023/7/11
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {

    /**
     * 名称--唯一值
     */
    String name() default StringUtil.EMPTY;

    /**
     * 是否打印入参
     */
    boolean printParam() default true;

    /**
     * 是否打印出参
     */
    boolean printResult() default true;

    /**
     * 方法类型
     */
    MethodType methodType() default MethodType.DEFAULT;

}

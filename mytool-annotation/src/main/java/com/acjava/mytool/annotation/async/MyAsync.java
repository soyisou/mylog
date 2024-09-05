package com.acjava.mytool.annotation.async;

import com.acjava.mytool.core.util.StringUtil;

import java.lang.annotation.*;

/**
 * @Description: 自定义异步注解，替代Spring的@Async
 * @author: loujm
 */
@Target({ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAsync {

    /**
     *  传入线程池的BeanName<br/>
     *  不传将使用默认的线程池
     */
    String value() default StringUtil.EMPTY;

    /**
     * 默认传递traceId
     */
    boolean traceId() default true;

}

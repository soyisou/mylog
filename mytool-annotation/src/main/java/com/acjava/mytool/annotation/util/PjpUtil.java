package com.acjava.mytool.annotation.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Description: ProceedingJoinPoint工具类
 * @author: loujm
 * @date: 2023/7/11
 */
public class PjpUtil {

    /**
     * 通过ProceedingJoinPoint获取方法上面的注解
     * @param pjp ProceedingJoinPoint对象
     * @param annotationClass 想要获取的目标注解的字节码对象
     * @return 目标注解对象
     * @param <T>
     */
    public static <T extends Annotation> T getAnno(ProceedingJoinPoint pjp, Class<T> annotationClass) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(annotationClass);
    }
}

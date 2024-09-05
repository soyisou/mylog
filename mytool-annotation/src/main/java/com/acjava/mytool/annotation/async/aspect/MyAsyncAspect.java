package com.acjava.mytool.annotation.async.aspect;

import com.acjava.mytool.annotation.async.MyAsync;
import com.acjava.mytool.annotation.util.PjpUtil;
import com.acjava.mytool.core.util.StringUtil;
import com.acjava.mytool.core.util.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * @Description: 异步注解切面类
 * @author: loujm
 */
@Slf4j
@Aspect
public class MyAsyncAspect {

    @Resource
    private SpringUtil springUtil;

    @Resource(name = "defaultPool")
    private Executor defaultPool;

    @Pointcut("@annotation(com.acjava.mytool.annotation.async.MyAsync)")
    public void myAsyncPointcut(){}

    @Around("myAsyncPointcut()")
    public Object around(ProceedingJoinPoint pjp) {
        MyAsync annotation = PjpUtil.getAnno(pjp, MyAsync.class);

        Executor executor = defaultPool;
        if (StringUtil.isNotEmpty(annotation.value())) {
            Object executePool = springUtil.getBean(annotation.value());
            if (executePool != null &&  executePool instanceof Executor) {
                executor = (Executor) executePool;
            } else {
                log.error("自定义线程池异常，将使用默认的线程池！自定义线程池为：{}", annotation.value());
            }
        }
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        executor.execute(() -> {
            if (annotation.traceId() && !CollectionUtils.isEmpty(contextMap)) {
                MDC.setContextMap(contextMap);
            }
            try {
                pjp.proceed();
            } catch (Throwable e) {
                log.error("异步执行业务代码异常：{}", e);
            }
        });
        return null;
    }
}

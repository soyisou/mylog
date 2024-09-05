package com.acjava.mytool.trace.supprot.xxl;

import com.acjava.mytool.trace.util.TraceUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author loujm
 * @date 2023/8/7 - 14:29
 */
@Slf4j
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
public class XxlJobAddTraceAspect {

    @Pointcut("@annotation(com.xxl.job.core.handler.annotation.XxlJob)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        try {
            TraceUtil.startTrace();
        } catch (Exception e) {
            log.error("开启链路失败[xxl-job], 错误信息为: {}", e);
        }

        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            log.error("执行业务代码异常：{}", e);
        } finally {
            TraceUtil.clear();
        }
        return result;
    }
}

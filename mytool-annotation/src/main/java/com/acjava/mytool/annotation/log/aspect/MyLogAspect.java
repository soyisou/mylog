package com.acjava.mytool.annotation.log.aspect;

import com.acjava.mytool.annotation.log.MyLog;
import com.acjava.mytool.annotation.log.bean.MyLogBean;
import com.acjava.mytool.annotation.log.constant.LogConstant;
import com.acjava.mytool.annotation.util.PjpUtil;
import com.acjava.mytool.core.common.model.Result;
import com.acjava.mytool.core.util.JsonUtil;
import com.acjava.mytool.core.util.StringUtil;
import com.acjava.mytool.core.util.collection.CollectionUtil;
import com.acjava.mytool.core.util.collection.MapUtil;
import com.acjava.mytool.core.util.time.LocalDateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import java.util.Map;

/**
 * @Description: 统一日志处理切面类
 * @author: loujm
 * @date: 2023/7/11
 */
@Slf4j
@Aspect
public class MyLogAspect {

    private String app;
    private String env;

    public MyLogAspect(String app, String env) {
        this.app = app;
        this.env = env;
    }

    /** 将该切面打印的日志 指定输出为MyLog类型 */
    public static Logger myLogger = LoggerFactory.getLogger("MY_LOG");

    @Pointcut("@annotation(com.acjava.mytool.annotation.log.MyLog)")
    public void myLogPointcut(){}

    @Around("myLogPointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 获取方法上的@MyLog注解
        MyLog myLog = PjpUtil.getAnno(pjp, MyLog.class);
        MyLogBean logBean = MyLogBean.builder()
                .name(myLog.name())
                .methodType(myLog.methodType())
                .thread(Thread.currentThread().getName())
                .time(LocalDateTimeUtil.nowMs())
                .app(app)
                .env(env)
                .traceId(MDC.get(LogConstant.TRACE_ID))
                .spanId(MDC.get(LogConstant.SPAN_ID)) // todo
                .userId(MDC.get(LogConstant.USER_ID)) // todo
                .remoteIp(MDC.get(LogConstant.IP))
                .httpCode(MDC.get(LogConstant.HTTP_CODE))
                .build();

        if (StringUtil.isEmpty(logBean.getName())) {
            String className = pjp.getTarget().getClass().getSimpleName();
            String methodName = ((MethodSignature) pjp.getSignature()).getMethod().getName();
            String defaultName = className + "." + methodName;
            logBean.setName(defaultName);
        }

        long begin, end;
        Object result = null;
        boolean success = true;
        begin = System.currentTimeMillis();
        try {
            result = pjp.proceed();
            return result;
        } catch (Throwable e) {
            result = e.toString();
            success = false;
            throw e;
        } finally {
            end = System.currentTimeMillis();
            if (myLog.printParam()) {
                logBean.setParam(genParam(pjp));
            }
            if (myLog.printResult()) {
                logBean.setResult(result);
            }
            logBean.setRt(end - begin);
            logBean.setSuccess(genSuccess(success, result));
            logBean.setResultSize(genResultSize(result));

            try {
                myLogger.info(JsonUtil.toJsonString(logBean));
            } catch (Exception e) {
                log.error("日志注解有异常 ", e);
            }
        }

    }

    private Object genParam(ProceedingJoinPoint pjp) {
        try {
            // 获取参数名
            String[] parameterNames = ((MethodSignature)pjp.getSignature()).getParameterNames();
            // 获取参数值
            Object[] args = pjp.getArgs();
            Map<String, Object> paramMap = MapUtil.newHashMap();
            for (int i = 0; i < args.length; i++) {
                paramMap.put(parameterNames[i], args[i]);
            }
            return paramMap;
        } catch (Exception e) {
            log.error("aop genParam exception,{}", pjp.toString(), e);
            return MapUtil.empty();
        }
    }

    private boolean genSuccess(boolean success, Object result) {
        if (!success) {
            return false;
        }
        if (result == null) {
            return true;
        }
        if (result instanceof Result) {
            return ((Result<?>) result).isSuccess();
        }
        return true;
    }

    private int genResultSize(Object result) {
        // 默认的解析规则
        if (result == null) {
            return 0;
        }
        if (result instanceof Result) {
            return calSize(((Result<?>) result).getData());
        }
        return calSize(result);
    }

    private int calSize(Object data) {
        try {
            return CollectionUtil.size(data);
        } catch (Exception e) {
            // 如果不是集合类型，默认size为1
            return 1;
        }
    }

}

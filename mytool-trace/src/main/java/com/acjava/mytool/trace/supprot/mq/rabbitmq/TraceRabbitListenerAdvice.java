package com.acjava.mytool.trace.supprot.mq.rabbitmq;

import com.acjava.mytool.trace.util.TraceUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.amqp.core.Message;
import java.util.List;
import java.util.Map;

/**
 * @author loujm
 */
public class TraceRabbitListenerAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Message message;
        // 反射拿到 Message
        Object argument = invocation.getArguments()[1];
        if (argument instanceof List) {
            message = ((List<? extends Message>) argument).get(0);
        } else {
            message = (Message) argument;
        }
        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        String traceId = (String) headers.get("traceId");
        TraceUtil.setTraceId(traceId);
        Throwable error = null;
        try {
            return invocation.proceed();
        } catch (Throwable t) {
            error = t;
            throw t;
        } finally {
            if (error != null) System.out.println("把错误存入span: " + error.getCause());
            TraceUtil.clear();
        }
    }
}

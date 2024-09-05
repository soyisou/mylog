package com.acjava.mytool.trace.supprot.feign;

import com.acjava.mytool.annotation.log.constant.LogConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;

/**
 * feign.RequestInterceptor是在使用Feign进行微服务之间的通信时，用于拦截请求并对其进行处理的接口。它可以在发出Feign请求之前或之后，对请求进行修改、添加头信息或者进行其他自定义操作。
 *
 * @Description: Feign拦截器，将traceId加到请求头里
 * @author: loujm
 * @date: 2023/7/12
 */
public class FeignAddTraceIdInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String traceId = MDC.get(LogConstant.TRACE_ID);
        requestTemplate.header(LogConstant.TRACE_ID, traceId);
        MDC.clear();
    }
}

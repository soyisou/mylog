package com.acjava.mytool.trace.util;

import com.acjava.mytool.annotation.log.constant.LogConstant;
import com.acjava.mytool.core.util.RandomUtil;
import com.acjava.mytool.core.util.StringUtil;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Description: 开启链路的工具类
 * @author: loujm
 * @date: 2023/7/12
 */
public class TraceUtil {

    public static final List<String> HEADER_IPS = Arrays.asList("X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "X-Real-IP");

    public static final String TRACE_ID = "traceId";

    public static String getRemoteIp(HttpServletRequest request) {
        Objects.requireNonNull(request);
        String ip = HEADER_IPS.stream().map(request::getHeader).filter(TraceUtil::isValidIp).findFirst().orElse(StringUtil.EMPTY);
        if (StringUtil.isBlank(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
    private static boolean isValidIp(String ip) {
        if (StringUtil.isBlank(ip)) {
            return false;
        } else {
            return !"unknown".equalsIgnoreCase(ip);
        }
    }

    /**
     * <ul>
     *     <li>请求头没traceId：表示是外部请求，生成新的traceId，保存到线程变量</li>
     *     <li>请求头有traceId：表示是内部请求，取出头部的traceId，保存到线程变量（达到传递traceId的目的）</li>
     * </ul>
     */
    public static void startTrace(HttpServletRequest httpRequest) {
        String traceId = StringUtil.EMPTY;
        if (httpRequest != null) {
            traceId = httpRequest.getHeader(LogConstant.TRACE_ID);
        }
        setTraceId(traceId);
    }

    /**
     * 开启链路追踪功能
     */
    public static String startTrace() {
        String traceId = MDC.get(LogConstant.TRACE_ID);
        if (StringUtil.isEmpty(traceId)) {
            traceId = genTraceId();
            MDC.put(LogConstant.TRACE_ID, traceId);
        }
        return traceId;
    }

    public static void setTraceId(String traceId) {
        if (StringUtil.isEmpty(traceId)) {
            traceId = genTraceId();
        }
        MDC.put(LogConstant.TRACE_ID, traceId);
    }

    /**
     * 生成traceId
     */
    public static String genTraceId() {
        return RandomUtil.randomString(30);
    }

    /**
     * 清除traceId，一般用于线程池里的线程。
     */
    public static void clear() {
        MDC.clear();
    }

    public static String getTraceId() {
        return MDC.get(TRACE_ID);
    }
}

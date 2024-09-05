package com.acjava.mytool.trace.supprot.http;

import com.acjava.mytool.annotation.log.constant.LogConstant;
import com.acjava.mytool.trace.util.TraceUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
* @Description: 给http请求添加traceId
* @author: loujm
* @date: 2023/7/12
*/
@WebFilter(
        filterName = "HttpAddTraceIdFilter",
        urlPatterns = {"/*"}
)
@Order(Integer.MIN_VALUE)
@Slf4j
public class HttpAddTraceIdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        try {
            this.before(servletRequest);
            chain.doFilter(servletRequest, servletResponse);
            this.after(servletResponse);
        } catch (Exception e) {
            log.error("HttpAddTraceIdFilter过滤器执行异常", e);
        } finally {
           MDC.clear();
        }
    }

    private void after(ServletResponse servletResponse) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
            if (httpServletResponse == null) {
                return;
            }
            MDC.put(LogConstant.HTTP_CODE, String.valueOf(httpServletResponse.getStatus()));
        } catch (Exception e) {
            log.error("after处理异常", e);
        }
    }

    private void before(ServletRequest servletRequest) {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        // 开启链路追踪
        TraceUtil.startTrace(httpRequest);

        String method = "";
        String url = "";
        String protocol = "";
        String ip = "";
        if (httpRequest != null) {
            method = httpRequest.getMethod();
            url = httpRequest.getRequestURI();
            protocol = httpRequest.getProtocol();
            ip = TraceUtil.getRemoteIp(httpRequest);
        }

        MDC.put(LogConstant.METHOD, method);
        MDC.put(LogConstant.PROTOCOL, protocol);
        MDC.put(LogConstant.URL, url);
        MDC.put(LogConstant.IP, ip);
    }

}

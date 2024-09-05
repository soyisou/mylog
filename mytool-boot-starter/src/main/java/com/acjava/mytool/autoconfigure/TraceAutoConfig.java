package com.acjava.mytool.autoconfigure;

import com.acjava.mytool.trace.supprot.feign.FeignAddTraceIdInterceptor;
import com.acjava.mytool.trace.supprot.http.HttpAddTraceIdFilter;
import com.acjava.mytool.trace.supprot.xxl.XxlJobAddTraceAspect;
import com.xxl.job.core.handler.annotation.XxlJob;
import feign.Feign;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.Filter;

/**
 * @Description: 链路追踪的自动配置类
 * @author: loujm
 * @date: 2023/7/12
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "mytool.trace", name = "enabled", havingValue = "true", matchIfMissing = true)
public class TraceAutoConfig {

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(Feign.class)
    public static class FeignAddTraceIdConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public FeignAddTraceIdInterceptor addTraceIdInterceptor() {
            return new FeignAddTraceIdInterceptor();
        }
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(Filter.class)
    public static class HttpAddTraceConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public FilterRegistrationBean<HttpAddTraceIdFilter> httpAddTraceIdFilter() {
            FilterRegistrationBean<HttpAddTraceIdFilter> registrationBean = new FilterRegistrationBean<>();
            registrationBean.setFilter(new HttpAddTraceIdFilter());
            registrationBean.addUrlPatterns("/*");
            registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
            return registrationBean;
        }
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(XxlJob.class)
    public static class XxlJobAddTraceConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public XxlJobAddTraceAspect xxlJobAddTraceAspect() {
            return new XxlJobAddTraceAspect();
        }
    }

}

package com.acjava.mytool.autoconfigure;

import com.acjava.mytool.core.util.spring.SpringUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author loujm
 * @date 2023/8/7 - 15:37
 */
@Configuration(proxyBeanMethods = false)
public class SpringUtilAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public SpringUtil springUtil(){
        return new SpringUtil();
    }
}

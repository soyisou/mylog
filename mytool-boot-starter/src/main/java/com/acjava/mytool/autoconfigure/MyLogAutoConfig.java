package com.acjava.mytool.autoconfigure;

import com.acjava.mytool.annotation.log.aspect.MyLogAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: @MyLog自动配置类
 * @author: loujm
 * @date: 2023/7/12
 */

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({EnvProperties.class})
@ConditionalOnProperty(prefix = "mytool.my-log", name = "enabled", havingValue = "true", matchIfMissing = true)
public class MyLogAutoConfig {

    private EnvProperties envProperties;

    public MyLogAutoConfig(EnvProperties envProperties) {
        this.envProperties = envProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public MyLogAspect myLogAspect() {
        return new MyLogAspect(envProperties.getApplicationName(), envProperties.getProfilesActive());
    }
}

package com.acjava.mytool.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: 获取app名和环境名
 * @author: loujm
 * @date: 2023/7/11
 */
@Data
@ConfigurationProperties(prefix = "spring")
public class EnvProperties {

    /**
     * 读取配置文件的值----spring.application.name
     */
    private String applicationName;

    /**
     * 读取配置文件的值----spring.profiles.active
     */
    private String profilesActive;

}

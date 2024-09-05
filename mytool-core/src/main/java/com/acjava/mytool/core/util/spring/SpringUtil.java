package com.acjava.mytool.core.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description: Spring工具类
 * @author: loujm
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    public <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }
}

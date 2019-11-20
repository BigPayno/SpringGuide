package com.payno.springguide.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

/**
 * @author payno
 * @date 2019/11/20 10:41
 * @description
 */
@Configuration
public class TestConfiguration implements ApplicationContextAware {
    ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }

    @Bean
    public ClassLoader classLoader(){
        return context.getClassLoader();
    }

    @Component
    public static class Service{

    }

    @Bean
    public ResourceClassLoader resourceClassLoader(){
        return new ResourceClassLoader(ClassUtils.getDefaultClassLoader());
    }
}

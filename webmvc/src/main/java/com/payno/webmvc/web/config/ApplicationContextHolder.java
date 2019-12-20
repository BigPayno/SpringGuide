package com.payno.webmvc.web.config;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2019/12/19 08:49
 * @description
 */
@Getter
@Component
public class ApplicationContextHolder implements ApplicationContextAware {
    ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context=applicationContext;
    }
}

package com.payno.transaction.Test;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2019/11/23 15:21
 * @description
 */
@Component
public class ContextHolder implements ApplicationContextAware {
    ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
    public ApplicationContext get(){
        return applicationContext;
    }
}

package com.payno.boot.config.binder;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2019/12/27 15:30
 * @description
 *      Environment？？
 */
@Component
public class BindRunner implements ApplicationRunner, ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }

    @Autowired
    AbstractBeanBinder beanBinder;
    ApplicationContext context;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BinderUser binderUser=new BinderUser();
        BinderGeneric<String> binderGeneric=new BinderGeneric<>();
        beanBinder.bind(binderUser);
        beanBinder.bind(binderGeneric);
        System.out.println(binderUser);
        System.out.println(binderGeneric);
        System.out.println(beanBinder.environment);
    }
}

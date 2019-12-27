package com.payno.boot.config.binder;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * @author payno
 * @date 2019/12/27 14:50
 * @description
 */
abstract class AbstractBeanBinder implements EnvironmentAware {
    Environment environment;
    Binder binder;
    @Override
    public void setEnvironment(Environment environment) {
        this.environment=environment;
    }
    @PostConstruct
    public void initBinder(){
        binder=Binder.get(environment);
    }

    public <T> T bind(T t){
        boolean hasAnnotation=AnnotatedElementUtils.hasAnnotation(t.getClass(),ConfigurationProperties.class);
        if(hasAnnotation){
            ResolvableType type=resolvableTypeOf(t);
            return bindInternal(t,resolvableTypeOf(t));
        }else{
            return t;
        }
    }

    public abstract <T> T bindInternal(T t, ResolvableType resolvableType);
    public abstract <T> ResolvableType resolvableTypeOf(T t);
}

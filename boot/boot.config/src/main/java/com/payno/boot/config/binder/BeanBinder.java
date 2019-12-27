package com.payno.boot.config.binder;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2019/12/27 15:20
 * @description
 */
@Component
public class BeanBinder extends AbstractBeanBinder{
    @Override
    public <T> T bindInternal(T t, ResolvableType resolvableType) {
        ConfigurationProperties configurationProperties=
                AnnotationUtils.findAnnotation(t.getClass(),ConfigurationProperties.class);
        binder.bind(
                configurationProperties.prefix(),
                Bindable.of(resolvableType).withExistingValue(t)
        );
        return t;
    }

    @Override
    public  <T> ResolvableType resolvableTypeOf(T t) {
        return ResolvableType.forInstance(t);
    }
}

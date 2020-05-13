package com.payno.jpa.data.common.resolver;

import com.payno.jpa.data.common.entity.Data;
import com.payno.jpa.data.entity.NzCheck;
import org.springframework.core.ResolvableType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author payno
 * @date 2020/5/13 11:22
 * @description
 */
public final class Resolvers {

    private Resolvers(){}

    static Map<Class<?>,DataResolver<?>> resolverMap;

    static {
        resolverMap = new HashMap<>();
        resolverMap.put(NzCheck.class,new NzCheckResolver());
    }

    public static <T extends Data<T>> DataResolver<T> resolver(Class<T> clazz){
        DataResolver<T> dataResolver= (DataResolver<T>) resolverMap.get(clazz);
        Objects.requireNonNull(dataResolver);
        return dataResolver;
    }

    public static <T extends Data<T>> void resolve(T t){
        resolver(t.getClass()).resolve(t);
    }
}

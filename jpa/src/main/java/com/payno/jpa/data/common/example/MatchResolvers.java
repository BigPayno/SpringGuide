package com.payno.jpa.data.common.example;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * @author payno
 * @date 2020/5/13 10:06
 * @description
 */
public final class MatchResolvers{

    static List<MatchResolver<?>> matchResolvers;

    private MatchResolvers(){
        matchResolvers = new ArrayList<>();
        matchResolvers.add(new IgnoreResolver());
        matchResolvers.add(new StringResolver());
    }

    public static <T> Example<T> resolve(T t) {
        MatchContext context=new MatchContext();
        context.setClazz(t.getClass());
        context.setExampleMatcher(ExampleMatcher.matching());
        matchResolvers.forEach(matchResolver -> matchResolver.resolve(context));
        return Example.of(t,context.exampleMatcher);
    }
}

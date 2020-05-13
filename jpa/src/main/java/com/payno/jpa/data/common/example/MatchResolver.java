package com.payno.jpa.data.common.example;

import org.springframework.data.domain.ExampleMatcher;

/**
 * @author payno
 * @date 2020/5/13 09:20
 * @description
 */
public interface MatchResolver<T> {
    /**
     * resolve
     * @author: payno
     * @time: 2020/5/13 09:28
     * @description: 解析object的注解
     * @param context
     * @return: void
     */
    void resolve(MatchContext context);
}

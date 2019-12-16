package com.payno.jpa.demo;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

/**
 * @author payno
 * @date 2019/11/28 11:19
 * @description
 */
public interface ToExample {
    String[] ignoreProperties();

    <T extends ToExample> Example<T> toExample(ExampleMatcher matcher);

    <T extends ToExample> Example<T> toExample();
}

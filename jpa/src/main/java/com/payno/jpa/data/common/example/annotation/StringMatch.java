package com.payno.jpa.data.common.example.annotation;

import org.springframework.data.domain.ExampleMatcher;

import java.lang.annotation.*;

/**
 * @author payno
 * @date 2020/5/13 09:25
 * @description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(StringMatchs.class)
@ExampleMatch(order = 1,name = "字符串匹配")
public @interface StringMatch {
    ExampleMatcher.StringMatcher matcher() default ExampleMatcher.StringMatcher.DEFAULT;
}

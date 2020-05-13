package com.payno.jpa.data.common.example.annotation;

import java.lang.annotation.*;

/**
 * @author payno
 * @date 2020/5/13 09:25
 * @description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ExampleMatch(order = 0,name = "忽略匹配")
public @interface IgnoreMatch {
    String[] ignoreCase() default {};
}

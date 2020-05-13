package com.payno.jpa.data.common.example.annotation;

import java.lang.annotation.*;

/**
 * @author payno
 * @date 2020/5/13 09:30
 * @description
 */
@Target({ElementType.ANNOTATION_TYPE,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExampleMatch {
    int order();
    String name() default "匹配";
}

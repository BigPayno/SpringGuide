package com.payno.webmvc.web.annotation;

import java.lang.annotation.*;

/**
 * @author payno
 * @date 2019/11/29 09:15
 * @description
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encryption {
    char replace() default '*';
    int from() default -1;
    int to() default -1;
}

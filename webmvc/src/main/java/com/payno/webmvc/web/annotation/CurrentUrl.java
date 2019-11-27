package com.payno.webmvc.web.annotation;

import java.lang.annotation.*;

/**
 * @author payno
 * @date 2019/11/27 10:43
 * @description
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUrl {
    String sessionKey() default "url";
    String headerKey() default "Authorization";
    String paramKey() default "abc";
}
package com.payno.springguide.spring.enable.autoconfig;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author payno
 * @date 2019/11/22 09:06
 * @description
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
// @Import(HelloWorldImportSelector.class)
@Import(HelloConfig.class)
public @interface EnableHelloWorld {
}

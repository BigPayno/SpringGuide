package com.payno.springguide.spring.enable.autoconfig;

import org.springframework.context.annotation.Configuration;

/**
 * @author payno
 * @date 2019/11/22 09:04
 * @description
 */
@Configuration
@EnableHelloWorld
//@ConditionalOnMissingBean(name = "helloConfig")
public class HelloAutoConfig {
}

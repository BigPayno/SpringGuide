package com.payno.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author payno
 * @date 2019/12/23 10:26
 * @description
 */
@SpringBootApplication
@ComponentScan(basePackages = "com")
public class SwaggerBoot {
    public static void main(String[] args) {
        SpringApplication.run(SwaggerBoot.class, args);
    }
}

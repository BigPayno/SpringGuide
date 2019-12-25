package com.payno.dubbo.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author payno
 * @date 2019/12/25 14:49
 * @description
 */
@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
public class DubboProducerApp {
    public static void main(String[] args) {
        SpringApplication.run(DubboProducerApp.class,args);
    }
}

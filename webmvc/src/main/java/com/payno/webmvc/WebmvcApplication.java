package com.payno.webmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com")
public class WebmvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebmvcApplication.class, args);
    }

}

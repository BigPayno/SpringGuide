package com.payno.https;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HttpsApplication {
    public static void main(String[] args) {
        SpringApplication.run(HttpsApplication.class, args);
    }
    @GetMapping("/https")
    public void test(){
        System.out.println("hello,https");
    }
}

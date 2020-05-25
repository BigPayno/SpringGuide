package com.payno.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author payno
 * @date 2020/5/25 11:23
 * @description
 */
@SpringBootApplication
@RestController
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostMapping("/hello/{name}")
    public String test(@PathVariable("name") String name){
            return "hello,"+name;
    }

    @GetMapping("/hello")
    public String test2(HttpServletRequest request){
        return request.getParameter("name");
    }
}

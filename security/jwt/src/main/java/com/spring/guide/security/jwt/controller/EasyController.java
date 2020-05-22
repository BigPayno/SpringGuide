package com.spring.guide.security.jwt.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author payno
 * @date 2020/5/21 11:21
 * @description
 */
@RestController
@RequestMapping("api")
public class EasyController {
    @PostMapping("/hello")
    public String hello(){
        return "hello";
    }
}

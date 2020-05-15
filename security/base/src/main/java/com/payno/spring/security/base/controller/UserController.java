package com.payno.spring.security.base.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author payno
 * @date 2020/5/15 09:34
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    /**
     *  不懂为什么RolesAllow貌似不一样
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/lock")
    public String lock() { return "lock";}
}

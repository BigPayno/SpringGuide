package com.payne.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author payno
 * @date 2019/12/4 11:12
 * @description
 */
@RestController
@RequestMapping
public class AuthorizationController {
    @RequiresPermissions("admin:query")
    @PostMapping("/admin/query")
    public void query(){
        System.out.println("query successful");
    }
}

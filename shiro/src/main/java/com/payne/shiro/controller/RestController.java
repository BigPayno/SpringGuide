package com.payne.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author payno
 * @date 2019/12/4 16:52
 * @description
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("rest")
public class RestController {
    @RequiresPermissions("admin:query")
    @PostMapping("/admin/query")
    public void query(){
        System.out.println("query successful");
    }
}

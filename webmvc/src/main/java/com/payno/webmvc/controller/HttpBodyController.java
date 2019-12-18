package com.payno.webmvc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author payno
 * @date 2019/12/18 16:01
 * @description
 */
@RestController
@RequestMapping("body")
public class HttpBodyController {
    @PostMapping(value = "/payno")
    public String easyPayno(@RequestBody String body){
        System.out.println(body);
        return body;
    }

    @PostMapping(value = "/text")
    public String easyText(@RequestBody String body){
        System.out.println(body);
        return body;
    }
}

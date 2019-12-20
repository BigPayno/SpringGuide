package com.payno.webmvc.controller;

import com.payno.webmvc.web.annotation.Encryption;
import com.payno.webmvc.web.domain.Response;
import com.payno.webmvc.web.dto.Secret;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author payno
 * @date 2019/11/29 09:18
 * @description
 */
@RestController
@RequestMapping("secret/")
public class SecretController {
    @GetMapping("1")
    @Encryption
    public Response<?> secret(){
        System.out.println(this.getClass());
        return Response.of(new Secret("payno1314"));
    }
}

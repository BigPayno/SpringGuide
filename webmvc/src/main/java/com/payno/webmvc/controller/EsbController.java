package com.payno.webmvc.controller;

import com.payno.webmvc.web.dto.esb.EsbXo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author payno
 * @date 2019/12/19 11:33
 * @description
 */
@RestController
@RequestMapping("esb/")
public class EsbController {
    @PostMapping("request")
    public EsbXo request(@RequestBody EsbXo esbXo){
        System.out.println("load request success!");
        System.out.println(esbXo);
        return esbXo;
    }
}

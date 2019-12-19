package com.payno.webmvc.controller;

import com.payno.webmvc.web.config.ApplicationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.*;

/**
 * @author payno
 * @date 2019/12/18 16:01
 * @description
 */
@RestController
@RequestMapping("body")
public class HttpBodyController{
    @Autowired
    ApplicationContextHolder holder;

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

    @GetMapping(value = "/all")
    public void supportBody(){
        holder.getContext().getBeansOfType(HttpMessageConverter.class)
                .entrySet().forEach(entry->{
            System.out.printf("Converter[%s]:[%s]%n",entry.getKey(),entry.getValue());
        });
    }

    @PostMapping(value = "/stream")
    public byte[] easyStream(){
        return "hello".getBytes();
    }

    @PostMapping(value = "/stream/upload")
    public void easyStreamUpload(@RequestBody byte[] bytes){
        System.out.println(new String(bytes));
    }
}

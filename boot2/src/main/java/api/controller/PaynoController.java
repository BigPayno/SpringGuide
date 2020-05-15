package api.controller;

import api.property.Property;
import api.property.Property2;
import api.response.ResponseAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author payno
 * @date 2020/5/8 16:28
 * @description
 */
@RestController
@ResponseAdvice
public class PaynoController {

    @Autowired
    Property property;

    @Autowired
    Property2 property2;

    @PostMapping("/payno")
    public String payno(){
        return "payno";
    }

    @PostMapping("/error")
    public String error(){
        throw new NullPointerException();
    }

    @GetMapping("/refresh")
    public String name(){
        return property.getName()+" "+property2.getName();
    }
}

package api.controller;

import api.response.ResponseAdvice;
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
    @PostMapping("/payno")
    public String payno(){
        return "payno";
    }

    @PostMapping("/error")
    public String error(){
        throw new NullPointerException();
    }
}

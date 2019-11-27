package com.payno.webmvc.controller;

import com.payno.webmvc.web.annotation.CurrentUrl;
import com.payno.webmvc.web.annotation.Log;
import com.payno.webmvc.web.domain.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author payno
 * @date 2019/11/26 17:02
 * @description
 */
@RestController
@RequestMapping("exception/")
public class ExceptionController {
    @GetMapping("{flag}")
    @Log
    public Response<Boolean> var0(@PathVariable("flag")@CurrentUrl String flag) throws Exception{
        System.out.println("hello!");
        System.out.println(flag);
        if("0".equals(flag)){
            throw new Exception();
        }
        return Response.of(Boolean.TRUE);
    }
    @GetMapping("1")
    public Response<Boolean> var1() throws Exception{
        System.out.println(1);
        return Response.of(Boolean.TRUE);
    }

    @GetMapping("2")
    public Response<Boolean> var2(@CurrentUrl String host) throws Exception{
        System.out.println(host);
        return Response.of(Boolean.TRUE);
    }
}

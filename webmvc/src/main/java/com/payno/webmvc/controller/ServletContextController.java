package com.payno.webmvc.controller;

import com.payno.webmvc.service.ServletContextService;
import com.payno.webmvc.web.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author payno
 * @date 2019/11/28 16:46
 * @description
 */
@RestController
@RequestMapping("j2ee/")
public class ServletContextController {
    @Autowired
    ServletContextService contextService;
    @PostMapping("context")
    public Response context() {
        contextService.display();
        return Response.ok();
    }
}

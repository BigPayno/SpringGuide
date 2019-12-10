package com.example.session.mvc;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author payno
 * @date 2019/12/10 09:34
 * @description
 */
@Profile("mvc-session")
@RestController
@RequestMapping
public class MvcController {
    @GetMapping
    public void sessionIsCreate(HttpSession httpSession){
        System.out.println(httpSession.getClass());
        System.out.println(httpSession.getAttribute("abc"));
    }
}

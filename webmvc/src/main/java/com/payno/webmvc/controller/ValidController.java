package com.payno.webmvc.controller;

import com.payno.webmvc.web.dto.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author payno
 * @date 2019/11/26 16:38
 * @description
 */
@RestController
@Validated
public class ValidController {
    @GetMapping("test1")
    public String test1(
            @NotBlank(message = "{required}") String name,
            @Email(message = "{invalid}") String email) {
        return "success";
    }

    @GetMapping("test2")
    public String test2(@Valid User user) {
        return "success";
    }
}

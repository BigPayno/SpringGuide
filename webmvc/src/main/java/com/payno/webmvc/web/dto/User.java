package com.payno.webmvc.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author payno
 * @date 2019/11/26 16:38
 * @description
 */
@Getter
@Setter
public class User {
    @NotBlank(message = "{required}")
    private String name;

    @Email(message = "{invalid}")
    private String email;
}

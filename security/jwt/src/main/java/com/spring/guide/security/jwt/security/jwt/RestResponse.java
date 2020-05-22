package com.spring.guide.security.jwt.security.jwt;

import lombok.Data;

import java.io.Serializable;

/**
 * @author payno
 * @date 2020/5/21 11:38
 * @description
 */
@Data
public class RestResponse implements Serializable {
    private String status;
    private String msg;
    private Object result;
    private String jwtToken;
}

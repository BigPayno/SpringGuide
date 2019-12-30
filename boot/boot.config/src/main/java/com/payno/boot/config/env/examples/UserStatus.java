package com.payno.boot.config.env.examples;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author payno
 * @date 2019/12/30 10:05
 * @description
 */
@Getter
@AllArgsConstructor
public enum  UserStatus{
    LOGIN_STATUS("登陆",1),
    LOG_OUT_STATUS("离线",2);
    private String type;
    private int status;
}

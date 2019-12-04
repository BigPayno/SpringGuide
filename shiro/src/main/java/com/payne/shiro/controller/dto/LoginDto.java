package com.payne.shiro.controller.dto;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

/**
 * @author payno
 * @date 2019/12/3 16:58
 * @description
 */
@Data
public class LoginDto implements Serializable {
    private static final long serialVersionUID = -3207880482640325843L;
    private String userName;
    private String password;
    private Boolean rememberMe;
    public UsernamePasswordToken toToken(){
        rememberMe=(rememberMe==null)?false:rememberMe;
        return new UsernamePasswordToken(userName,password,rememberMe);
    }
}

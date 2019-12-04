package com.payne.shiro.components;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2019/12/4 16:46
 * @description
 */
@Component
public class TokenDecoder {
    public UsernamePasswordToken toToken(String token){
        return new UsernamePasswordToken("payno","2789");
    }
}

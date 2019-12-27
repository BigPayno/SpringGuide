package com.payno.boot.auto;

import com.payno.boot.api.Print;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author payno
 * @date 2019/12/27 10:03
 * @description
 */
@Configuration
public class AutoConfig {
    @Bean
    public Print printA(){
        return new PrintA();
    }
}

package com.payno.springguide.spring.enable.autoconfig;

import com.payno.springguide.spring.enable.bootstrap.People;
import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;

/**
 * @author payno
 * @date 2019/11/22 08:56
 * @description
 */
@Configuration
//@Import(People.class)
//@DependsOn("springUtils")
public class HelloConfig{
    @Bean
    public People hello(){
        return new People("hello");
    }
}

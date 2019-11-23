package com.payno.transaction.Test;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2019/11/23 15:19
 * @description
 */
@Component
@Setter
public class Config {
    @Autowired
    Bean bean;
    public void print(){
        System.out.println(bean.name);
    }
}

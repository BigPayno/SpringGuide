package com.payno.springguide.spring.enable.bootstrap;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author payno
 * @date 2019/11/22 09:20
 * @description
 */
@Data
@AllArgsConstructor
public class People {
    private String name;
    public void print(){
        System.out.println(this);
    }
}

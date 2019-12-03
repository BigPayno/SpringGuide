package com.payno.transaction.demo;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author payno
 * @date 2019/12/3 11:33
 * @description
 */
@Component
public class Service {
    @Transactional(rollbackFor = Exception.class)
    public void run(String val) throws Exception{
        val="hello";
        throw new Exception();
    }
}

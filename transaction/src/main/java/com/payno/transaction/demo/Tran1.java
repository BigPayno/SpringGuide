package com.payno.transaction.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author payno
 * @date 2020/1/16 15:22
 * @description
 */
@Component
@Primary
public class Tran1 implements ITran{
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void init(Obj obj) throws Exception{
        obj.setVal("abc");
        if(obj.getVal()!=null){
            throw new Exception();
        }
    }
}

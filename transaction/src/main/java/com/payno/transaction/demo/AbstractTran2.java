package com.payno.transaction.demo;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author payno
 * @date 2020/1/16 15:39
 * @description
 */
public abstract class AbstractTran2 implements ITran{
    @Override
    public void init(Obj obj) throws Exception {
        initInternal();
    }

    @Transactional
    protected void initInternal(){

    }
}

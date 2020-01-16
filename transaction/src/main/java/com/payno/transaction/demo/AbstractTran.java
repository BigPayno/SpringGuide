package com.payno.transaction.demo;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author payno
 * @date 2020/1/16 15:35
 * @description
 */
public abstract class AbstractTran implements ITran{
    @Override
    @Transactional
    public void init(Obj obj) throws Exception {
        System.out.println();
    }
}

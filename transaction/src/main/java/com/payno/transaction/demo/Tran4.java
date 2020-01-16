package com.payno.transaction.demo;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author payno
 * @date 2020/1/16 15:42
 * @description
 */
@Component
public class Tran4 extends AbstractTran2{
    @Override
    @Transactional
    public void initInternal() {
        super.initInternal();
    }
}

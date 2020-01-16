package com.payno.transaction.demo;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author payno
 * @date 2020/1/16 15:21
 * @description
 */
public interface ITran {
    void init(Obj obj) throws Exception;
}

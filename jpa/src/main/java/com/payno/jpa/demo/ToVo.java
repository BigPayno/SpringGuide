package com.payno.jpa.demo;

import javax.naming.OperationNotSupportedException;

/**
 * @author payno
 * @date 2019/11/28 10:37
 * @description
 */
public interface ToVo {
    <T extends ToVo> BaseDataVo<T> toVo() throws OperationNotSupportedException;
}

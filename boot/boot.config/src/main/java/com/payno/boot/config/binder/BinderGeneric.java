package com.payno.boot.config.binder;

import lombok.Data;

/**
 * @author payno
 * @date 2019/12/27 11:13
 * @description
 */
@Data
public class BinderGeneric<T> {
    T t;
}

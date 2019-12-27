package com.payno.boot.config.binder;

import lombok.Data;

import java.util.List;

/**
 * @author payno
 * @date 2019/12/27 11:29
 * @description
 */
@Data
public class BindGenericCollection {
    List<BinderUser> users;
}

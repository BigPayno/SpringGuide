package com.payno.boot.commmon.binder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author payno
 * @date 2019/12/27 10:58
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BinderUser {
    String name;
    String password;
}

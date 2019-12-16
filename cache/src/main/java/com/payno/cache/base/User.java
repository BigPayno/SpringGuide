package com.payno.cache.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author payno
 * @date 2019/12/16 16:08
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{
    private Long id;
    private String name;
}

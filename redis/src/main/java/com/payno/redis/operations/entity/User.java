package com.payno.redis.operations.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author payno
 * @date 2019/12/1 16:45
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private String psd;
}

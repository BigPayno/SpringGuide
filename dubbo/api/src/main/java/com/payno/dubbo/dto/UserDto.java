package com.payno.dubbo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author payno
 * @date 2019/12/25 14:38
 * @description
 */
@Data
public class UserDto implements Serializable {
    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 昵称
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;
}

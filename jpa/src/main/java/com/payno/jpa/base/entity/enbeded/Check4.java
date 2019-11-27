package com.payno.jpa.base.entity.enbeded;

import javax.persistence.Column;

/**
 * @author payno
 * @date 2019/11/27 16:17
 * @description
 */
public class Check4 {
    @Column(updatable = false, insertable = false)
    private String idCard;
    private String name;
    private String mobile;
    private String channel;
}

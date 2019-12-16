package com.payno.jpa.base.entity.enbeded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author payno
 * @date 2019/7/29 09:11
 * @description 联合主键;过短时间内保存多个会导致一样;
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RowKeyPK implements Serializable {
    private String IdCard;
    private long timestamp;

    public static RowKeyPK build(String idCard) {
        return new RowKeyPK(idCard, System.currentTimeMillis());
    }
}

package com.payno.jpa.data.entity;

import com.payno.jpa.data.common.entity.AbstractData;
import com.payno.jpa.data.common.entity.DataVo;
import com.payno.jpa.data.entity.vo.NzCheckVo;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author payno
 * @date 2020/5/13 10:41
 * @description
 */
@Data
@Entity
@Table(name = "data_nz_check")
public class NzCheck extends AbstractData<NzCheck> {

    boolean fake;

    @Override
    public NzCheck get() {
        return this;
    }

    @Override
    public DataVo<NzCheck> toVo() {
        return new NzCheckVo(fake);
    }
}

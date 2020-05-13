package com.payno.jpa.data.entity.vo;

import com.payno.jpa.data.common.entity.DataVo;
import com.payno.jpa.data.entity.NzCheck;
import lombok.AllArgsConstructor;

/**
 * @author payno
 * @date 2020/5/13 10:43
 * @description
 */
@AllArgsConstructor
public class NzCheckVo implements DataVo<NzCheck> {
    boolean fake;
}

package com.payno.jpa.data.common.resolver;

import com.payno.jpa.data.common.entity.Data;

/**
 * @author payno
 * @date 2020/5/13 10:49
 * @description
 */
public interface DataResolver<T extends Data<T>> {
    /**
     * resolve
     * @author: payno
     * @time: 2020/5/13 10:50
     * @description:
     * @param t
     * @return:
     */
    void resolve(T t);
}

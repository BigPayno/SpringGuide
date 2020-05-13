package com.payno.jpa.data.common.request;

import com.payno.jpa.data.common.entity.Data;

/**
 * @author payno
 * @date 2020/5/13 11:04
 * @description
 */
public interface Sink<S,T extends Data<T>> {
    /**
     * request
     * @author: payno
     * @time: 2020/5/13 11:06
     * @description:
     * @param context
     * @return: T
     */
    T request(SourceContext<S> context);
}

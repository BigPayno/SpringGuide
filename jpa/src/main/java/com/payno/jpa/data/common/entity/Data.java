package com.payno.jpa.data.common.entity;

import org.springframework.data.domain.Example;

/**
 * @author payno
 * @date 2020/5/13 09:09
 * @description
 */
public interface Data<T extends Data<T>> {

    void setNativeData(String data);

    /**
     * get
     * @author: payno
     * @time: 2020/5/13 10:16
     * @description:
     * @param
     * @return: T
     */
    T get();

    /**
     * toVo
     * @author: payno
     * @time: 2020/5/13 09:11
     * @description: 返回Vo对象
     * @param
     * @return: DataVo<T>
     */
    DataVo<T> toVo();

    /**
     * toExample
     * @author: payno
     * @time: 2020/5/13 10:15
     * @description:
     * @param
     * @return: org.springframework.data.domain.Example<T>
     */
    Example<T> toExample();
}

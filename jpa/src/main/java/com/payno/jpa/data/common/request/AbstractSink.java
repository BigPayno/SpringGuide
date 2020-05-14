package com.payno.jpa.data.common.request;

import com.payno.jpa.data.common.entity.Data;
import com.payno.jpa.data.common.resolver.Resolvers;
import org.springframework.beans.BeanUtils;

/**
 * @author payno
 * @date 2020/5/13 11:21
 * @description
 */
public abstract class AbstractSink<S,T extends Data<T>> implements Sink<S,T> {

    @Override
    public T request(SourceContext<S> context) {
        T t=getT(context);
        t.setNativeData(requestInterval(context));
        Resolvers.resolve(t);
        return t;
    }

    /**
     * requestInterval
     * @author: payno
     * @time: 2020/5/13 11:24
     * @description:    获取String将用于解析得到T
     * @param context
     * @return: String
     */
    abstract String requestInterval(SourceContext<S> context);

    /**
     * getT
     * @author: payno
     * @time: 2020/5/13 11:30
     * @description:    通过Source获得初始化的T
     * @param context
     * @return: T
     */
    abstract T getT(SourceContext<S> context);
}

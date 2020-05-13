package com.payno.jpa.data.common.request;

import com.google.gson.Gson;
import com.payno.jpa.data.common.resolver.NzCheckResolver;
import com.payno.jpa.data.entity.NzCheck;
import org.springframework.beans.BeanUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @author payno
 * @date 2020/5/13 11:06
 * @description
 */
public class NzCheckSink extends AbstractSink<BaseSource,NzCheck> {

    @Override
    String requestInterval(SourceContext<BaseSource> context) {
        Gson gson=new Gson();
        RestTemplate rest =new RestTemplate();
        return rest.postForObject("http://localhost:9527/mock", gson.toJson(context.s),String.class);
    }

    @Override
    NzCheck getT(SourceContext<BaseSource> context) {
        NzCheck nzCheck=new NzCheck();
        BeanUtils.copyProperties(context.getS(),nzCheck);
        return nzCheck;
    }
}

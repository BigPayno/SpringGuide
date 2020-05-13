package com.payno.jpa.data.common.resolver;

import com.jayway.jsonpath.JsonPath;
import com.payno.jpa.data.entity.NzCheck;

/**
 * @author payno
 * @date 2020/5/13 10:51
 * @description
 */
public class NzCheckResolver implements DataResolver<NzCheck>{

    static final String FAKE_JSON_PATH="$.status";
    static final String IS_NOT_FAKE="ok";

    @Override
    public void resolve(NzCheck nzCheck) {
        String fake = JsonPath.using(ResolverContext.VAL_RESOLVE_CONFIG)
                .parse(nzCheck.getNativeData()).read(FAKE_JSON_PATH,String.class);
        nzCheck.setFake(!IS_NOT_FAKE.equals(fake));
    }
}

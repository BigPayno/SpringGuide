package com.payno.jpa.data.common.resolver;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.jayway.jsonpath.spi.mapper.GsonMappingProvider;
import org.springframework.data.domain.Page;

/**
 * @author payno
 * @date 2020/5/13 10:55
 * @description
 */
public final class ResolverContext {
    private ResolverContext(){}

    public static final Configuration PATH_RESOLVE_CONFIG;
    public static final Configuration VAL_RESOLVE_CONFIG;

    static {
        PATH_RESOLVE_CONFIG=Configuration.defaultConfiguration()
                //  返回Path而非Value，可以结合..实现更复杂的功能
                .addOptions(Option.AS_PATH_LIST).jsonProvider(new GsonJsonProvider()).mappingProvider(new GsonMappingProvider());
        VAL_RESOLVE_CONFIG=Configuration.defaultConfiguration()
                // 如果Path指向的Value不存在，返回null而非抛出错误
                .addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL).jsonProvider(new GsonJsonProvider()).mappingProvider(new GsonMappingProvider());
    }
}

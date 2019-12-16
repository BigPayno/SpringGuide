package com.payno.cache.base;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2019/12/16 16:57
 * @description
 *      知道缓存注解可以搭配Spel表达式即可
 *      用到再看，如果要实现特别复杂逻辑可以自定义注解加切面
 */
@Component
@CacheConfig(cacheNames = "user")
public class ConditionCacheService {
    public static boolean canCachePut(Cache cache,Long id,String name){
        if("payno".equals(name)&&id<20){
            return true;
        }
        return false;
    }

    /**
     * eq，ne，lt，le，gt，ge
     * ==  !=  <   <=   >  >=
     */
    @CachePut( key = "#user.id", condition = "#user.id eq 10")
    public User conditionFindById(final User user){
        return user;
    }

    /**
     * #result表示方法返回值
     */
    @CachePut(key = "#user.id", condition = "#result.username ne 'payno'")
    public User conditionSave(final User user){
        return user;
    }

    @CachePut(key = "#user.id",condition = "#root.target.canCachePut(#root.caches[0], #user.id, #user.name)")
    public User save(User user){
        return user;
    }
}

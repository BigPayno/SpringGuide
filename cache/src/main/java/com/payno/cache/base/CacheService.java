package com.payno.cache.base;

import org.springframework.cache.annotation.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author payno
 * @date 2019/12/16 16:06
 * @description
 *      conditions和unless前者是对传入的参数进行筛选，后者可以对返回值进行筛选
 */
@Component
@CacheConfig(cacheNames = "user")
public class CacheService {
    Map<Long,User> userMap=new HashMap<>();
    /**
     * String[] value();
     * 缓存的名字，可以把数据写到多个缓存
     * String key() default "";
     * 缓存key，如果不指定将使用默认的KeyGenerator生成，后边介绍
     * String condition() default "";
     * 满足缓存条件的数据才会放入缓存，condition在调用方法之前和之后都会判断
     * String unless() default "";
     * 用于否决缓存更新的，不像condition，该表达只在方法执行之后判断，此时可以拿到返回值result进行判断了
     * boolean sync() default false;
     * CachePut注解是先调用方法，然后将方法的返回值放入到缓存中。
     */
    @CachePut(key = "#user.id")
    public User save(User user){
        System.out.println(user+" save!");
        userMap.put(user.getId(),user);
        return user;
    }

    /**
     *boolean allEntries() default false;
     * 是否移除所有数据
     *boolean beforeInvocation() default false;
     * 是调用方法之前移除/还是调用之后移除
     */
    @CacheEvict(key = "#user.id")
    public void delete(User user){
        System.out.println(user+" delete!");
        userMap.remove(user.getId());
    }

    @CacheEvict(allEntries = true)
    public void deleteAll(){
        System.out.println("remove all！");
        userMap.clear();
    }

    @Cacheable( key = "#id")
    public User findById(final Long id) {
        System.out.println("cache miss, invoke find by id, id:" + id);
        return userMap.get(id);
    }

    @Caching(
            put = {
                    @CachePut(key = "#user.id"),
                    @CachePut(key = "#user.name")
            }
    )
    public User saveM(User user){
        System.out.println("save !");
        userMap.put(user.getId(),user);
        return user;
    }
}

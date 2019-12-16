package com.payno.cache.base;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

/**
 * @author payno
 * @date 2019/12/16 16:00
 * @description
 *      Cache->ConcreteCache
 *      CacheManager->ConcreteCache
 *      @EnableCaching -> Annotations
 */
public class ManagerGuide {
    public static void main(String[] args) {
        CacheManager cacheManager=new ConcurrentMapCacheManager();
        Cache cache=cacheManager.getCache("payno");
        cache.put("name","payno");
        cache.put("pwd","2789");
        cacheManager.getCacheNames().forEach(System.out::println);
    }
}

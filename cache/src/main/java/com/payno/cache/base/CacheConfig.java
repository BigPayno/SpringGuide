package com.payno.cache.base;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author payno
 * @date 2019/12/16 15:21
 * @description
 */
@EnableCaching
@Configuration
public class CacheConfig extends CachingConfigurerSupport{
    @Override
    public CacheManager cacheManager() {
        return manager();
    }

    @Override
    public CacheResolver cacheResolver() {
        return null;
    }

    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return null;
    }

    /**
     * ConcurrentMapCacheManager/ConcurrentMapCacheFactoryBean
     * GuavaCacheManager；
     * EhCacheCacheManager/EhCacheManagerFactoryBean；
     * JCacheCacheManager/JCacheManagerFactoryBean；
     * 除了GuavaCacheManager之外，其他Cache都支持Spring事务的，即如果事务回滚了，Cache的数据也会移除掉。
     */
    @Bean
    public CacheManager manager(){
        return new ConcurrentMapCacheManager();
    }

    /**
     * ConcurrentMapCache：使用java.util.base.ConcurrentHashMap实现的Cache；
     * GuavaCache：对Guava com.google.common.cache.Cache进行的Wrapper，需要Google Guava 12.0或更高版本，@since spring 4；
     * EhCacheCache：使用Ehcache实现
     * JCacheCache：对javax.cache.Cache进行的wrapper，@since spring 3.2；spring4将此类更新到JCache 0.11版本；
     */
}

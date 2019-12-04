package com.payne.shiro.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author payno
 * @date 2019/12/4 09:15
 * @description
 *    只是返回给客户端一个RememberMe的Cookie而已，没有特殊的操作
 */
@Configuration
public class SecurityManagerSupport {
    @Bean
    @ConditionalOnProperty(prefix = "shiro",name = "remember_me",havingValue = "true")
    public RememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        // 设置cookie名称，对应login.html页面的<input type="checkbox" name="rememberMe"/>
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        // 设置cookie的过期时间，单位为秒，这里为一天
        cookie.setMaxAge(86400);
        cookieRememberMeManager.setCookie(cookie);
        // rememberMe cookie加密的密钥
        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }
    @Bean
    @ConditionalOnProperty(prefix = "shiro.cache",name = "type",havingValue = "redis",matchIfMissing = true)
    public RedisManager redisManager(){
        return new RedisManager();
    }

    @Bean
    @ConditionalOnProperty(prefix = "shiro.cache",name = "type",havingValue = "redis")
    public CacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * 实现缓存的切面组件，可能由于boot代理底层问题，只会重复方法
     * 建议shiro的redis缓存通过手写方法实现吧。。。，残念
     */
    @ConditionalOnProperty(prefix = "shiro.cache",name = "type",havingValue = "redis")
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    @ConditionalOnProperty(prefix = "shiro.cache",name = "enabled",havingValue = "session")
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * Session 管理
     *  暂时还有问题,换了新版本就没问题，大概因为Jedis方法的弃用吧
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "shiro.cache",name = "enabled",havingValue = "session")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionDAO(redisSessionDAO());
        defaultWebSessionManager.setGlobalSessionTimeout(3600 * 1000);
        defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
        return defaultWebSessionManager;
    }
}

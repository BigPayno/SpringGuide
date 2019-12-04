package com.payne.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author payno
 * @date 2019/12/4 09:04
 * @description
 */
@Slf4j
@Configuration
public class SecurityManagerConfig {
    @Autowired
    AuthorizingRealm authorizingRealm;

    @Autowired(required = false)
    RememberMeManager rememberMeManager;

    @Autowired(required = false)
    CacheManager cacheManager;

    @Autowired(required = false)
    SessionManager sessionManager;

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(authorizingRealm);
        if(rememberMeManager!=null){
            log.info("SecurityManager注入rememberMeManager");
            securityManager.setRememberMeManager(rememberMeManager);
        }
        if(cacheManager!=null){
            log.info("SecurityManager注入cacheManager");
            securityManager.setCacheManager(cacheManager);
        }
        if(sessionManager!=null){
            log.info("SecurityManager注入sessionManager");
            securityManager.setSessionManager(sessionManager);
        }
        return securityManager;
    }

}

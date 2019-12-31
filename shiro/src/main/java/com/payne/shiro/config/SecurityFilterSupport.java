package com.payne.shiro.config;

import com.payne.shiro.components.TokenDecoder;
import com.payne.shiro.components.TokenFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;

/**
 * @author payno
 * @date 2019/12/3 15:48
 * @description
 */
@Configuration
public class SecurityFilterSupport {
    @Autowired
    SecurityManager securityManager;
    @Autowired
    TokenDecoder tokenDecoder;

    /**
     * 非常重要一点，这里使用的FactoryBean，所以注入的Filter是在运行时生成的
     * 如果用组件注入，最后会报错
     * 这是基于Spring的类，继承了FactoryBean与PostProcessor
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        LinkedHashMap<String, Filter> filterLinkedHashMap=new LinkedHashMap<>();
        filterLinkedHashMap.put("token",new TokenFilter(tokenDecoder));
        shiroFilterFactoryBean.setFilters(filterLinkedHashMap);
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/rest/**", "token");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}

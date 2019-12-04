package com.payne.shiro.components;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import javax.annotation.PostConstruct;
import java.util.function.Consumer;

/**
 * @author payno
 * @date 2019/12/4 11:26
 * @description
 */
@NoArgsConstructor
@AllArgsConstructor
public class SecurityManagerRegistry {
    public SecurityManager securityManager;
    public Consumer<DefaultWebSecurityManager> registry;
    @PostConstruct
    public void initSecurityManager(){
        if(securityManager instanceof DefaultWebSecurityManager){
            DefaultWebSecurityManager webSecurityManager=(DefaultWebSecurityManager)securityManager;
            registry.accept(webSecurityManager);
        }
    }
}

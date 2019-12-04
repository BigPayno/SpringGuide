package com.payne.shiro.components;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author payno
 * @date 2019/12/3 15:57
 * @description
 *      看了一堆教程，缓存授权信息和认证信息还是自己集成吧
 *      没看到哪里缓存了
 */
@Slf4j
@Component
@Lazy
public class ShiroRealm extends AuthorizingRealm {
    /**
     * 权限验证
     * 通过PrincipalCollection获取当前对应的主体们，如邮箱，Id等
     * 通过该主体查询role和permission进行权限控制
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        System.out.println(isAuthorizationCachingEnabled());
        String user=(String)principal.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(Collections.emptySet());
        simpleAuthorizationInfo.setStringPermissions(Collections.singleton("admin:query"));
        log.debug("对[{}]进行权限验证,[{}]",user,getClass());
        return simpleAuthorizationInfo;
    }

    /**
     * 身份验证
     * AuthenticationToken暴露RememberMeAuthenticationToken的是Principal与Credentials
     * HostAuthenticationToken暴露的是host的get
     * RememberMeAuthenticationToken暴露的是isRememberMe的get
     * UsernamePasswordToken是shiro提供的实现，implements Host,RememberMe
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /**
         * 这里接收到的token就是Controller层，Subject.login(token)传过来的token
         * 其中Principal主要的，主角，Credentials证书、文凭
         */
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, password, getName());
        log.debug("对[{}]进行身份验证",userName);
        return info;
    }
}

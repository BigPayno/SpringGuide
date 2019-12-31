package com.payne.shiro.filters;

import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.servlet.AbstractShiroFilter;

import javax.servlet.Filter;
import java.util.EnumSet;

/**
 * @author payno
 * @date 2019/12/30 10:37
 * @description
 */
public class DefaultFiltersMain {
    private static void println(DefaultFilter filter){
        System.out.printf("Name:[%s],Type:[%s]%n",filter.toString(),filter.getFilterClass());
    }

    public static void main(String[] args) {
        EnumSet<DefaultFilter> filters=EnumSet.allOf(DefaultFilter.class);
        filters.forEach(DefaultFiltersMain::println);
    }

    /**
     * Shiro通过一个AbstractShiroFilter来实现安全控制
     * 存在两个实现:
     *      1.ShiroFilter
     *      2.SpringShiroFilter
     * 其中委托FilterChainResolver进行Shiro内置Filter链进行过滤
     *      1.anon 	org.apache.shiro.web.filter.authc.AnonymousFilter
     *      匿名登陆
     *      2.authc 	org.apache.shiro.web.filter.authc.FormAuthenticationFilter
     *      表单身份授权
     *      3.authcBasic 	org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
     *      http身份授权
     *      4.logout 	org.apache.shiro.web.filter.authc.LogoutFilter
     *      重定向登陆
     *      5.user 	org.apache.shiro.web.filter.authc.UserFilter
     *      用户
     *      6.noSessionCreation 	org.apache.shiro.web.filter.session.NoSessionCreationFilter
     *      创建Shiro Session
     *      7.perms 	org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter
     *      拥有指定权限的用户可以访问
     *      8.roles 	org.apache.shiro.web.filter.authz.RolesAuthorizationFilter
     *      拥有指定角色的用户可以访问
     *      9.port 	org.apache.shiro.web.filter.authz.PortFilter
     *      10.rest 	org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter
     *      11.ssl 	org.apache.shiro.web.filter.authz.SslFilter
     */
}

package com.payno.spring.security.base.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

/**
 * @author payno
 * @date 2020/5/15 09:38
 * @description
 */
@Configuration
/**
 *      开启注解，需要注意注解和Java Config是叠加的
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
                //  密码必须包含{加密类型@PasswordEncoderFactories}
                .withUser("admin").password("{noop}admin").roles("ADMIN")
                .and().withUser("user").password("{noop}user").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
            #(String... antPatterns) 方法，配置匹配的 URL 地址，基于 Ant 风格路径表达式 ，可传入多个。
            【常用】#permitAll() 方法，所有用户可访问。
            【常用】#denyAll() 方法，所有用户不可访问。
            【常用】#authenticated() 方法，登陆用户可访问。
            #anonymous() 方法，无需登陆，即匿名用户可访问。
            #rememberMe() 方法，通过 remember me 登陆的用户可访问。
            #fullyAuthenticated() 方法，非 remember me 登陆的用户可访问。
            #hasIpAddress(String ipaddressExpression) 方法，来自指定 IP 表达式的用户可访问。
            【常用】#hasRole(String role) 方法， 拥有指定角色的用户可访问。
            【常用】#hasAnyRole(String... roles) 方法，拥有指定任一角色的用户可访问。
            【常用】#hasAuthority(String authority) 方法，拥有指定权限(authority)的用户可访问。
            【常用】#hasAuthority(String... authorities) 方法，拥有指定任一权限(authority)的用户可访问。
            【最牛】#access(String attribute) 方法，当 Spring EL 表达式的执行结果为 true 时，可以访问。
            自定义登陆页面，可以通过 #loginPage(String loginPage) 方法，来进行设置。不过这里我们希望像「2. 快速入门」一样，使用默认的登陆界面
         */
        http
                .authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/user/**").hasAnyRole("ADMIN","USER")
                    .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().logout().permitAll();
    }
}

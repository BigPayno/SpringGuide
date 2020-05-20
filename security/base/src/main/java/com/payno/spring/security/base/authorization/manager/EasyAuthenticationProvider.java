package com.payno.spring.security.base.authorization.manager;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author payno
 * @date 2020/5/20 15:05
 * @description
 *      DaoAuthenticationProvider
 *          ->UserDetailsService
 *              UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException;
 *
 *          ->UserDetailsPasswordService
 *              UserDetails updatePassword(UserDetails var1, String var2);
 */
public class EasyAuthenticationProvider implements AuthenticationProvider {
    @Override
    /**
     *  验证
     */
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    /**
     *  aClass 支持的Authentication类型
     */
    public boolean supports(Class<?> aClass) {
        return false;
    }
}

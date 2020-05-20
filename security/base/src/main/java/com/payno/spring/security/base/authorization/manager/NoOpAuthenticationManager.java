package com.payno.spring.security.base.authorization.manager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author payno
 * @date 2020/5/20 10:32
 * @description
 *      授权管理器
 */
public class NoOpAuthenticationManager implements AuthenticationManager {
    private NoOpAuthenticationManager() {
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        throw new AuthenticationServiceException("Cannot authenticate " + authentication);
    }
}

package com.payno.spring.security.base.authorization;

import org.springframework.security.core.Authentication;

/**
 * @author payno
 * @date 2020/5/20 10:58
 * @description
 */
public class AuthenticationGuide {
    /**
     *  Principal
     *      String getName()
     *      boolean implies(Subject subject)
     *  Authentication extends Principal, Serializable
     *      Collection<? extends GrantedAuthority> getAuthorities();
     *          GrantedAuthority
     *              String getAuthority()
     *      Object getCredentials();
     *      Object getDetails();
     *      Object getPrincipal();
     *      boolean isAuthenticated();
     *      void setAuthenticated(boolean var1) throws IllegalArgumentException;
     *  AbstractAuthenticationToken implements Authentication, CredentialsContainer（资格证书容器）
     *     private final Collection<GrantedAuthority> authorities;
     *     private Object details;
     *     private boolean authenticated = false;
     */
}

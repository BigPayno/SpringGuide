package com.payno.spring.security.base.filter;

import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.x509.X509AuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 *@author payno
 *@date 2020/5/22 10:19
 *@description
 */
public class AuthorizationFiltersGuide {
    public static void main(String[] args) {
        Class<?>[] base = new Class[]{
                UsernamePasswordAuthenticationFilter.class,
                AnonymousAuthenticationFilter.class
        };

        Class<?>[] oauthAuth = new Class[]{
                BasicAuthenticationFilter.class,
                AnonymousAuthenticationFilter.class,
                UsernamePasswordAuthenticationFilter.class,
                AnonymousAuthenticationFilter.class
        };

        Class<?>[] oauthResource = new Class[]{
                OAuth2AuthenticationProcessingFilter.class,
                AnonymousAuthenticationFilter.class,
                UsernamePasswordAuthenticationFilter.class,
                AnonymousAuthenticationFilter.class
        };
    }
}
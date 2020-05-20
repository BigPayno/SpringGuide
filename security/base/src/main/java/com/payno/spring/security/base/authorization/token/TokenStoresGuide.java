package com.payno.spring.security.base.authorization.token;

import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author payno
 * @date 2020/5/20 11:26
 * @description
 */
public class TokenStoresGuide {
    /**
     *  TokenStore
     *
     *  OAuth2Authentication readAuthentication(String token);
     *      OAuth2Authentication readAuthentication(OAuth2AccessToken token);
     *      基于模板方法readAuthentication(String token)，通过token获得OAuth2Authentication
     *
     *  OAuth2AccessToken readAccessToken(String tokenValue);
     *      通过token获得OAuth2AccessToken
     *
     *  OAuth2AccessToken getAccessToken(OAuth2Authentication authentication);
     *      根据OAuth2Authentication找回一个已经存储的OAuth2AccessToken，否则返回空
     *
     *   OAuth2RefreshToken readRefreshToken(String tokenValue);
     *
     *   OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token);
     *
     *  void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication);
     *  void removeAccessToken(OAuth2AccessToken token);
     *  void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication);
     *  void removeRefreshToken(OAuth2RefreshToken token);
     *  void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken);
     *
     *
     *  Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName);
     *  Collection<OAuth2AccessToken> findTokensByClientId(String clientId);
     */
    public static void main(String[] args) {
        Class<?>[] classes =new Class<?>[]{
            InMemoryTokenStore.class, RedisTokenStore.class, JwtTokenStore.class, JwkTokenStore.class, JdbcTokenStore.class
        };
    }
}

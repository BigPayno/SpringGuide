package com.payno.spring.security.base.authorization.token;

/**
 * @author payno
 * @date 2020/5/20 11:13
 * @description
 */
public class TokenServicesGuide {

    /**
     *  DefaultTokenServices implements
     *      AuthorizationServerTokenServices, ResourceServerTokenServices,ConsumerTokenServices,
     *      InitializingBean
     *
     *  <>->TokenStore,ClientDetailsService,TokenEnhancer,AuthenticationManager
     *
     *  RemoteTokenServices implements
     *      ResourceServerTokenServices
     */

    /**
     *   ResourceServerTokenServices
     *
     * 	 Load the credentials for the specified access token.
     * 	 @param accessToken The access token value.
     * 	 @return The authentication for the access token.
     * 	 @throws AuthenticationException If the access token is expired
     * 	 @throws InvalidTokenException if the token isn't valid
     * 	 OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException;
     *      @tokenStore-DefaultTokenServices throws InvalidTokenException
     *      1.根据token-value得到token
     *      2.根据token获得authentication
     *      3.返回authentication
     * 	 Retrieve the full access token details from just the value.
     * 	 @param accessToken the token value
     * 	 @return the full access token with client id etc.
     *   OAuth2AccessToken readAccessToken(String accessToken);
     *
     */


    /**
     *    AuthorizationServerTokenServices
     *
     *    Create an access token associated with the specified credentials.
     * 	  @param authentication The credentials associated with the access token.
     * 	  @return The access token.
     * 	  @throws AuthenticationException If the credentials are inadequate.
     *    OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException;
     *
     * 	 Refresh an access token. The authorization request should be used for 2 things (at least): to validate that the
     * 	 client id of the original access token is the same as the one requesting the refresh, and to narrow the scopes
     * 	 (if provided).
     * 	 @param refreshToken The details about the refresh token.
     * 	 @param tokenRequest The incoming token request.
     * 	 @return The (new) access token.
     * 	 @throws AuthenticationException If the refresh token is invalid or expired.
     * 	 OAuth2AccessToken refreshAccessToken(String refreshToken, TokenRequest tokenRequest)
     * 			throws AuthenticationException;
     *
     * 	 Retrieve an access token stored against the provided authentication key, if it exists.
     * 	 @param authentication the authentication key for the access token
     * 	 @return the access token or null if there was none
     * 	 OAuth2AccessToken getAccessToken(OAuth2Authentication authentication);
     */

    /**
     *   ConsumerTokenServices
     *   boolean revokeToken(String tokenValue);
     *   废除token
     */
}

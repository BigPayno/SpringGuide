package oauth.authorization;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @author payno
 * @date 2020/5/19 15:31
 * @description
 *  下面是配置一个授权服务必须要实现的endpoints：
 *     AuthorizationEndpoint：用来作为请求者获得授权的服务，默认的URL是/oauth/authorize.
 *     TokenEndpoint：用来作为请求者获得令牌（Token）的服务，默认的URL是/oauth/token.
 *
 *  下面是配置一个资源服务必须要实现的过滤器：
 *     OAuth2AuthenticationProcessingFilter：用来作为认证令牌（Token）的一个处理流程过滤器。
 *     只有当过滤器通过之后，请求者才能获得受保护的资源。
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /**
         * 配置授权相关端口
         */
        security
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("app1").secret("{noop}112233")
                .redirectUris("http://localhost:8080/callback")
                /**
                 *  authorization_code：授权码类型。
                 *  implicit：隐式授权类型。
                 *  password：资源所有者（即用户）密码类型。
                 *  client_credentials：客户端凭据（客户端ID以及Key）类型。
                 *  refresh_token：通过以上授权获得的刷新令牌来获取新的令牌
                 */
                .authorities("ROLE_CLIENT_TRUST")
                .authorizedGrantTypes("authorization_code")
                // 授权码
                .scopes( "read_contacts");
    }

    /**
     *  浏览器打开 http://localhost:8080/oauth/authorize?client_id=app1&response_type=code&scope=read_contacts
     *
     *     client_id 参数，必传，为我们在 OAuth2AuthorizationServer 中配置的 Client 的编号。
     *     redirect_url 参数，可选，回调地址。当然，如果 client_id 对应的 Client 未配置 redirectUris 属性，会报错。
     *     response_type 参数，必传，返回结果为授权码。
     *     scope 参数，可选，申请授权的 Scope 。如果多个，使用逗号分隔。
     *     state 参数，可选，表示客户端的当前状态，可以指定任意值，认证服务器会原封不动地返回这个值。
     *         未在上述 URL 中体现出来。
     */
}

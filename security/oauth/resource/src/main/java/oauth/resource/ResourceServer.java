package oauth.resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * @author payno
 * @date 2020/5/19 15:24
 * @description
 *  @EnableAuthorizationServer、@EnableResourceServer、SpringSecurity的URL处理规则
 * 请求优先由@EnableAuthorizationServer、@EnableResourceServer 处理，剩下的无法匹配的由SpringSecurity处理。
 */
@Configuration
@EnableResourceServer
/**
 *  EnableResourceServer自带 order=3
 *  而Security的order=100
 *  所以ResourceServerConfigurerAdapter会优先于WebSecurityConfigurerAdapter构建
 *  也就意味着对应的FilterChainChain会更早构造
 *  因此，如果一个请求既满足Security又满足Resource的安全控制，会优先处理Resource
 *  可以限定Resource的Filter路径
 */
public class ResourceServer extends ResourceServerConfigurerAdapter {

    @Bean
    /**
     * 不注入的话，会使用默认的服务
     *  最终会调用InMemoryTokenStore
     */
    public ResourceServerTokenServices resourceServerTokenServices(){
        RemoteTokenServices remoteTokenServices=new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
        remoteTokenServices.setClientId("app1");
        remoteTokenServices.setClientSecret("112233");
        return remoteTokenServices;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        /**
         *  该过滤链将处理/api/**
         *  此外的请求则优先security
         *
         *  一 URL匹配
         *
         *     requestMatchers() 配置一个request Mather数组，参数为RequestMatcher 对象，其match 规则自定义,需要的时候放在最前面，对需要匹配的的规则进行自定义与过滤
         *     authorizeRequests() URL权限配置
         *     antMatchers() 配置一个request Mather 的 string数组，参数为 ant 路径格式， 直接匹配url
         *     anyRequest 匹配任意url，无参 ,最好放在最后面
         *
         * 二 保护URL
         *
         *     authenticated() 保护UrL，需要用户登录
         *     permitAll() 指定URL无需保护，一般应用与静态资源文件
         *     hasRole(String role) 限制单个角色访问，角色将被增加 “ROLE_” .所以”ADMIN” 将和 “ROLE_ADMIN”进行比较. 另一个方法是hasAuthority(String authority)
         *     hasAnyRole(String… roles) 允许多个角色访问. 另一个方法是hasAnyAuthority(String… authorities)
         *     access(String attribute) 该方法使用 SPEL, 所以可以创建复杂的限制 例如如access("permitAll"), access("hasRole('ADMIN') and hasIpAddress('123.123.123.123')")
         *     hasIpAddress(String ipaddressExpression) 限制IP地址或子网
         *
         * 三 登录login
         *
         *     formLogin() 基于表单登录
         *     loginPage() 登录页
         *     defaultSuccessUrl 登录成功后的默认处理页
         *     failuerHandler登录失败之后的处理器
         *     successHandler登录成功之后的处理器
         *     failuerUrl登录失败之后系统转向的url，默认是this.loginPage + "?error"
         *
         * 四 登出logout
         *
         *     logoutUrl 登出url ， 默认是/logout， 它可以是一个ant path url
         *     logoutSuccessUrl 登出成功后跳转的 url 默认是"/login?logout"
         *     logoutSuccessHandler 登出成功处理器，设置后会把logoutSuccessUrl 置为null
         *
         */
        http
                .antMatcher("/api/**")
                    .authorizeRequests()
                        .antMatchers("/api/hello").access("#oauth2.hasScope('read_contacts')")
                        .antMatchers("/api/world").access("#oauth2.hasScope('write_contacts')")
                        .anyRequest().authenticated();
    }
}

package com.spring.guide.security.jwt.security.authorization;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

/**
 * @author payno
 * @date 2020/5/21 11:44
 * @description
 */
public final class Authorizations {
    public static UsernamePasswordAuthenticationToken loadToken(HttpServletRequest request, UserDetails userDetails){
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        /**
         *  加载spring.security.strategy配置，决定Holder存储策略
         *
         *      有一些应用并不适合使用ThreadLocal模式，那么还能不能使用SecurityContextHolder了呢？
         *      答案是可以的。SecurityContextHolder还提供了其他工作模式。
         *
         *      比如有些应用，像Java Swing客户端应用，它就可能希望JVM中所有的线程使用同一个安全上下文。
         *      此时我们可以在启动阶段将SecurityContextHolder配置成全局策略MODE_GLOBAL。
         *
         *      还有其他的一些应用会有自己的线程创建，并且希望这些新建线程也能使用创建者的安全上下文。
         *      这种效果，可以通过将SecurityContextHolder配置成MODE_INHERITABLETHREADLOCAL策略达到。
         *
         */
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}

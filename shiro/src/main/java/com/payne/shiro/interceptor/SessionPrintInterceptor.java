package com.payne.shiro.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.util.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @author payno
 * @date 2019/12/3 16:20
 * @description
 */
@Slf4j
@Component
public class SessionPrintInterceptor extends HandlerInterceptorAdapter {
    public static final String HANDLER_METHOD_KEY="handler method";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        request.setAttribute(HANDLER_METHOD_KEY,handlerMethod.getMethod().getName());
        log.info("当前HttpRequest类型:[{}]",request.getClass());
        log.info("当前HttpSession类型:[{}]",request.getSession().getClass());
        /**
         * 那么Session在何时创建呢？当然还是在服务器端程序运行的过程中创建的，
         * 不同语言实现的应用程序有不同创建Session的方法，
         * 而在Java中是通过调用HttpServletRequest的getSession方法（使用true作为参数）创建的。
         * 在创建了Session的同时，服务器会为该Session生成唯一的Session id，
         * 而这个Session id在随后的请求中会被用来重新获得已经创建的Session；
         * 在Session被创建之后，就可以调用Session相关的方法往Session中增加内容了，
         * 而这些内容只会保存在服务器中，发到客户端的只有Session id；
         * 当客户端再次发送请求的时候，会将这个Session id带上，
         * 服务器接受到请求之后就会依据Session id找到相应的Session，从而再次使用之。
         */
        log.info("注册HandlerMethod到Session[{}]!",request.getSession().getId());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HttpSession session=request.getSession();
        Enumeration<String> sessionArgs=session.getAttributeNames();
        log.info("当前HttpRequest类型:[{}]",request.getClass());
        log.info("当前HttpSession类型:[{}]",request.getSession().getClass());
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(
                String.format("%nSessionId[%s]请求访问方法[%s]",session.getId(),request.getAttribute(HANDLER_METHOD_KEY)));
        while(sessionArgs.hasMoreElements()){
            String key=sessionArgs.nextElement();
            stringBuilder.append(
                    String.format("%nSession 参数[%s]:[%s]",key,session.getAttribute(key)));
            if(session.getAttribute(key) instanceof SavedRequest){
                SavedRequest savedRequest=(SavedRequest) session.getAttribute(key);
                stringBuilder.append(
                        String.format("%nSaveRequest %s",savedRequest.getMethod()));
                stringBuilder.append(
                        String.format("%nSaveRequest %s",savedRequest.getQueryString()));
                stringBuilder.append(
                        String.format("%nSaveRequest %s",savedRequest.getRequestURI()));
            }
        }
        log.info(stringBuilder.toString());
        /**
         * Session 参数[org.apache.shiro.subject.support.DefaultSubjectContext_AUTHENTICATED_SESSION_KEY]:[true]
         * Session 参数[org.apache.shiro.web.session.HttpServletSession.HOST_SESSION_KEY]:[0:0:0:0:0:0:0:1]
         * Session 参数[org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY]:[payno]
         * 返回给请求的是Cookie  JSESSIONID
         */
        super.afterCompletion(request, response, handler, ex);
    }
}

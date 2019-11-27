package com.payno.webmvc.web.interceptor;

import com.payno.webmvc.web.annotation.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author payno
 * @date 2019/11/27 08:48
 * @description
 */
@Slf4j
@Component
public class LogAdapterInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod=(HandlerMethod)handler;
            Method method=handlerMethod.getMethod();
            Log logAnnotation=handlerMethod.getMethod().getDeclaredAnnotation(Log.class);
            if(logAnnotation!=null){
                /**
                 * 自定义注解，这里可以拿到方法与参数,注意如果要使用切面类可能会丢失类注解
                 */
                String uri=request.getRequestURI();
                log.info("请求路径:[{}],调用方法[{}]",uri,method);
            }

        }
        return super.preHandle(request, response, handler);
    }
}

package com.payno.webmvc.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author payno
 * @date 2019/11/26 16:32
 * @description
 *      preHandle：
 *      在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
 *      postHandle：
 *      在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，
 *      但未进行页面渲染），有机会修改ModelAndView （这个博主就基本不怎么用了）；
 *      afterCompletion：
 *      在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）；
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("处理拦截之前");
        httpServletRequest.setAttribute("startTime", System.currentTimeMillis());
        System.out.println(((HandlerMethod) o).getBean().getClass().getName());
        System.out.println(((HandlerMethod) o).getMethod().getName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("开始处理拦截");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("【拦截器】耗时 " + (System.currentTimeMillis() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("处理拦截之后");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("【拦截器】耗时 " + (System.currentTimeMillis()-start));
        System.out.println("异常信息 " + e);
    }
}

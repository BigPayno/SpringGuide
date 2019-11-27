package com.payno.webmvc.web.filter;

import com.google.common.base.Stopwatch;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author payno
 * @date 2019/11/26 16:29
 * @description
 */
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("开始执行过滤器");
        Stopwatch stopwatch=Stopwatch.createStarted();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("【过滤器】耗时 " + stopwatch.stop());
        System.out.println("结束执行过滤器");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}

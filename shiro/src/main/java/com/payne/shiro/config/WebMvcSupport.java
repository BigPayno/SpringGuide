package com.payne.shiro.config;

import com.payne.shiro.interceptor.SessionPrintInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author payno
 * @date 2019/12/3 16:29
 * @description
 */
@Configuration
public class WebMvcSupport extends WebMvcConfigurationSupport {
    @Autowired
    SessionPrintInterceptor sessionPrintInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionPrintInterceptor);
    }
}

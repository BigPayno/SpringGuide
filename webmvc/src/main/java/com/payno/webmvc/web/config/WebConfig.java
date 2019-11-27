package com.payno.webmvc.web.config;


import com.payno.webmvc.web.bind.UrlResolver;
import com.payno.webmvc.web.filter.TimeFilter;
import com.payno.webmvc.web.interceptor.LogAdapterInterceptor;
import com.payno.webmvc.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Collections;
import java.util.List;

/**
 * @author payno
 * @date 2019/11/26 16:24
 * @description
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Autowired
    TimeInterceptor timeInterceptor;
    @Autowired
    LogAdapterInterceptor logAdapterInterceptor;

    /**
     * WebFilter注册,也可以使用@WebFilter与@Component
     */
    @Bean
    public FilterRegistrationBean timeFilter() {
        TimeFilter timeFilter = new TimeFilter();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(timeFilter);
        filterRegistrationBean.setUrlPatterns(
                Collections.singletonList("/time/*")
        );
        return filterRegistrationBean;
    }


    /**
     * Interceptor注册
     *
     * 实现:实现接口，继承Adaptor
     * 注意这里的pattern匹配方式与Filter不同
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor).excludePathPatterns("/**");
        registry.addInterceptor(logAdapterInterceptor);
    }

    /**
     * 配置controller方法参数解析器
     */
    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UrlResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

    /**
     * 可以配置静态资源访问
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        super.addResourceHandlers(registry);
    }

    /**
    *
     * addMapping：配置可以被跨域的路径，可以任意配置，可以具体到直接请求路径。
     * allowedMethods：允许所有的请求方法访问该跨域资源服务器，如：POST、GET、PUT、DELETE等。
     * allowedOrigins：允许所有的请求域名访问我们的跨域资源，可以固定单条或者多条内容，
     * 如："http://www.baidu.com"，只有百度可以访问我们的跨域资源。
     * allowedHeaders：允许所有的请求header访问，可以自定义设置任意请求头信息，
     * 如："X-YAUTH-TOKEN"
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("GET");
        super.addCorsMappings(registry);
    }
}

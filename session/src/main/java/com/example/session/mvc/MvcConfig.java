package com.example.session.mvc;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Collections;

/**
 * @author payno
 * @date 2019/12/10 09:44
 * @description
 */
@Profile("mvc-session")
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new SessionFilter());
        filterRegistrationBean.setUrlPatterns(
                Collections.singletonList("/*")
        );
        return filterRegistrationBean;
    }
}

package com.payno.spring.security.base.filter;

import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;

/**
 * @author payno
 * @date 2020/5/22 10:27
 * @description
 *      打印当前服务的Filters
 *      并找到其中的FilterChainProxy打印其中的Filter
 */
@Component
public class FilterApp implements ApplicationRunner, ApplicationContextAware {

    ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        context.getBeansOfType(Filter.class).values().stream()
                .peek(System.out::println)
                .filter(filter -> filter.getClass()== FilterChainProxy.class)
                .map(filter -> (FilterChainProxy)filter)
                .flatMap(filterChainProxy -> filterChainProxy.getFilterChains().stream())
                .flatMap(securityFilterChain -> securityFilterChain.getFilters().stream())
                .forEach(System.err::println);
    }
}

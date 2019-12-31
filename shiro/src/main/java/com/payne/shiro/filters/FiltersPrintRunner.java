package com.payne.shiro.filters;

import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.lang.reflect.Field;

/**
 * @author payno
 * @date 2019/12/30 11:11
 * @description
 */
@Component
public class FiltersPrintRunner implements ApplicationRunner, ApplicationContextAware {
    ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        context.getBeansOfType(Filter.class).entrySet()
                .forEach(stringFilterEntry -> {
                    System.out.println("Bean: "+stringFilterEntry.getKey());
                    System.out.println("Class: "+stringFilterEntry.getValue().getClass());
                    if(stringFilterEntry.getValue() instanceof AbstractShiroFilter){
                        try {
                            System.out.println("-------------------------------------------------------------------");
                            AbstractShiroFilter abstractShiroFilter=(AbstractShiroFilter)stringFilterEntry.getValue();
                            PathMatchingFilterChainResolver filterChainResolver=(PathMatchingFilterChainResolver)abstractShiroFilter.getFilterChainResolver();
                            Field chainField= PathMatchingFilterChainResolver.class
                                    .getDeclaredField("filterChainManager");
                            chainField.setAccessible(true);
                            FilterChainManager filterChainManager=(FilterChainManager) chainField.get(filterChainResolver);
                            filterChainManager.getFilters().entrySet().forEach(entry->{
                                System.out.printf("Name:[%s],Type:[%s]%n",entry.getKey(),entry.getValue().getClass());
                            });
                            System.out.println("-------------------------------------------------------------------");
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }
}

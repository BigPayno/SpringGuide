package com.payno.springguide.spring;

import com.google.common.collect.ImmutableList;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2019/11/18 08:39
 * @description
 *      AbstractApplicationContext extends BeanFactory DefaultResourceLoader
 *
 *      interface ApplicationContext extends
 *      ->Lifecycle 生命周期
 *      ->ListableBeanFactory 获取Bean的能力
 *      ->HierarchicalBeanFactory 可继承的，获得父容器工厂
 *      ->MessageSource ？？？
 *      ->EnvironmentCapable 环境获取
 *        @FunctionalInterface
 *      ->ApplicationEventPublisher 发布事件
 *      ->ResourcePatternResolver 获取资源
 *      void refresh来更新，锁住startupShutdownMonitor
 *
 *     BeanDefinitionRegistry 注册BeanDefinition
 *     Aware 能够在初始化容器时候注入相关Bean到某个对象里
 *
 *     ApplicationContext是接口，暴露相关api，BeanFactory是容器，BeanDefinition是Domain
 *
 *     AnnotationConfigApplicationContext Spring3.0
 *
 */
public class ApplicationContextFrameworkGuide {
    @Configuration
    public static class Configurations implements ApplicationContextAware {
        public ApplicationContext applicationContext;
        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext=applicationContext;
        }

        @Bean
        public String str(){
            return "abcd";
        }
    }
    @Component
    @Data
    public static class Person{
        private String name;
        private String psw;
    }
    public static class Test{
        public static void main(String[] args) {
            AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(Configurations.class);
            annotationConfigApplicationContext.register(Person.class);
            System.out.println("------------------------------------");
            ImmutableList.copyOf(annotationConfigApplicationContext.getBeanDefinitionNames()).forEach(System.out::print);
        }
    }
}

package com.payno.springguide.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author payno
 * @date 2019/11/20 08:58
 * @description
 *      new->set->BeanNameAware->BeanFactoryAware->ApplicationContextAware
 *      ->BeanPostProcessor postProcessBeforeInitialization
 *      ->InitializationBean afterPropertiesSet  @Bean @Component
 *      ->调用定制的初始化方法 initMethod  @Bean
 *      ->BeanPostProcessor postProcessAfterInitialization
 *      ->Bean准备就绪 @Bean @Component
 *      ->DisposableBean destroy @Bean @Component
 *      ->调用定制的销毁方法 destroyMethod @Bean
 *
 *  @Component和@Bean都是用来注册Bean并装配到Spring容器中，
 *  但是Bean比Component的自定义性更强。可以实现一些Component实现不了的自定义加载类。
 */
public class BeanLifeCycleGuide {
    public static class BeanLifeCycle implements InitializingBean, DisposableBean {
        public BeanLifeCycle(){
            System.out.println("BeanLifeCycle::Constructor");
        }

        @PostConstruct
        public void postConstruct(){
            System.out.println("BeanLifeCycle::postConstruct");
        }

        private String name;

        @Autowired
        public void setName(String name){
            System.out.println("BeanLifeCycle::setName");
            this.name=name;
        }

        public void initMethod(){
            System.out.println("BeanLifeCycle::initMethod");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            System.out.println("BeanLifeCycle::afterPropertiesSet");
        }

        @Override
        public void destroy() throws Exception {
            System.out.println("BeanLifeCycle::destroy");
        }

        @PreDestroy
        public void preDestroy(){
            System.out.println("BeanLifeCycle::preDestroy");
        }

        public void destroyMethod(){
            System.out.println("BeanLifeCycle::destroyMethod");
        }
    }

    @Configuration
    public static class AppConfiguration{
        @Bean
        public String name(){
            return "BeanLifeCycle";
        }

        @Bean(initMethod = "initMethod",destroyMethod = "destroyMethod")
        public BeanLifeCycle beanLifeCycle(){
            System.out.println("Bean::Configuration");
            return new BeanLifeCycle();
        }

    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AppConfiguration.class);
        System.out.println(context.getBean(BeanLifeCycle.class).name);
        context.removeBeanDefinition("beanLifeCycle");
        /**
         * @Bean注入
         * 先执行Configuration中的@Bean下的方法，产生Bean
         * BeanLifeCycle::Constructor  构造器方法调用（Component注入才有）
         * BeanLifeCycle::setName  Setter方法注入
         * BeanLifeCycle::postConstruct 构造后置处理
         * BeanLifeCycle::afterPropertiesSet set后置处理 继承接口
         * BeanLifeCycle::initMethod 初始化方法 （Bean注入才有）
         * BeanLifeCycle
         * BeanLifeCycle::preDestroy 销毁前处理
         * BeanLifeCycle::destroy 销毁处理 继承接口
         * BeanLifeCycle::destroyMethod 销毁方法 （Bean注入才有）
         */
    }
}

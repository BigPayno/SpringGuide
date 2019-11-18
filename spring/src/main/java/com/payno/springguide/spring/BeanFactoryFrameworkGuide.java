package com.payno.springguide.spring;

import com.google.common.collect.ImmutableList;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2019/11/18 10:40
 * @description
 *  BeanFactory定义了BeanFactory最基础的一些操作
 *  AbstractBeanFactory extends BeanFactory implements ConfigurableBeanFactory
 *      ConfigurableBeanFactory 依赖管理
 *          extends SingletonBeanRegistry，HierarchicalBeanFactory
 *  FactoryBeanRegistrySupport
 *      <>->ConcurrentHashMap
 *              factoryBeanObjectCache
 *              mergedBeanDefinitions
 *          LinkedHashMap
 *              scopes
 *          CopyOnWriteArrayList
 *              beanPostProcessors
 *              ->BeanPostProcessor 在对象初始化前进行操作
 *              embeddedValueResolvers
 *  BeanPostProcessor的使用
 *  BeanFactoryProcessor的使用
 */
@Configuration
public class BeanFactoryFrameworkGuide {
    @Component("person")
    @Data
    public static class Person{
        private String name;
        private String pass;
    }

    @Component
    public static class FactoryPost implements BeanFactoryPostProcessor{
        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
            System.out.println("|||||||||post process before init factory!!|||||||||");
            ImmutableList.copyOf(configurableListableBeanFactory.getBeanDefinitionNames()).forEach(name->{
                System.out.println("[name]:"+name);
            });
            System.out.println("|||||||||post process before init factory!!|||||||||");
            BeanDefinition beanDefinition=configurableListableBeanFactory.getBeanDefinition("person");
            MutablePropertyValues propertyValues=beanDefinition.getPropertyValues();
            propertyValues.addPropertyValue("name","payno");
        }
    }

    @Component
    public static class BeanPost implements BeanPostProcessor{
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            System.out.printf("beanName[%s]:[%s]%n",beanName,bean.toString());
            return bean;
        }
    }
    public static class BeanFactoryPostTest{
        public static void main(String[] args) {
            AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(BeanFactoryFrameworkGuide.class);
            Person person=applicationContext.getBean("person",Person.class);
            System.out.println(person);
        }
    }
}

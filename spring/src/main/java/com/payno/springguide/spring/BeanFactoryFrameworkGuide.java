package com.payno.springguide.spring;

import org.springframework.beans.factory.BeanFactory;

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
 *  BeanPostProcessor
 *  BeanFactoryProcessor
 */
public class BeanFactoryFrameworkGuide {

}

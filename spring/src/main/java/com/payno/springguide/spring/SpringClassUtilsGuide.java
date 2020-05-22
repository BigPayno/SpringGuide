package com.payno.springguide.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ClassUtils;

/**
 * @author payno
 * @date 2019/11/20 10:41
 * @description
 *      由于双亲委派机制的存在，类加载器是无法重写源码加载器加载的类文件的，安全可靠
 */
public class SpringClassUtilsGuide {
    public void display(ClassLoader classLoader){
        System.out.println(classLoader.getClass());
        if(classLoader.getParent()!=null){
            display(classLoader.getParent());
        }
    }

    @Test
    public void parent() {
        ApplicationContext context=new AnnotationConfigApplicationContext(TestConfiguration.class);
        ClassLoader springLoader=context.getBean("classLoader",ClassLoader.class);
        ResourceClassLoader myLoader=context.getBean(ResourceClassLoader.class);
        TestConfiguration.Service service=context.getBean(TestConfiguration.Service.class);
        /**
         * 容器用AppClassLoader加载
         */
        System.out.println(springLoader.getClass());
        /**
         * Bean用AppClassLoader加载
         */
        System.out.println(service.getClass().getClassLoader().getClass());
        /**
         * 查看ClassLoader的父母Loader
         */
       display(myLoader);
       /**
        * AppClassLoader  load classpath java.class
        * ExtClassLoader load javax.class
        * BootstrapClassLoader load jre/jdk.class
        */
    }

    @Test
    public void localClass(){
        ApplicationContext context=new AnnotationConfigApplicationContext(TestConfiguration.class);
        /**
         * 测试AppLoader中是否包含其加载的ResourceLoader
         * 结果发现Spring加载的类都是通过默认加载器
         */
        System.out.println(context.getClassLoader());
        System.out.println(context.getBean(ResourceClassLoader.class).getClass().getClassLoader());
        System.out.println(context.getBean(TestConfiguration.Service.class).getClass().getClassLoader());
        System.out.println(ClassUtils.getDefaultClassLoader());

        /**
         * 查看双亲和子加载器是否会拥有自己加载的类的记录
         * 不会持有，换而言之，可以自定义classloader并判断加载的类是否通过自定义类加载器加载
         */
        System.out.println(
                ClassUtils.isPresent("com.payno.springguide.api.security.TestConfiguration",ClassUtils.getDefaultClassLoader().getParent())
        );
        System.out.println(
                ClassUtils.isPresent("java.utils.List",ClassUtils.getDefaultClassLoader())
        );
        System.out.println(
                ClassUtils.isPresent("com.payno.springguide.api.security.TestConfiguration",ClassUtils.getDefaultClassLoader())
        );
        /**
         * 不同加载器加载的类是不一样的
         * 哪怕同样的地址
         */
    }
}

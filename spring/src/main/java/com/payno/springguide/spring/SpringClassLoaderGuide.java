package com.payno.springguide.spring;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

/**
 * @author payno
 * @date 2019/11/18 14:18
 * @description
 *      Class.forName("jdkguide.print.PrintfGuide")会报错，因为会调默认的ClassLoader
 *      先掌握下classLoader这个抽象函数，在这基础上尝试去动态加载Spring容器里的组件
 *      refresh方法会进行BeanFactoryPostProcessor
 *      ConfigurationClassParser解析Import
 *      ConfigurationClassPostProcessor处理Configuration
 */
public class SpringClassLoaderGuide {
    public static class LoaderTest{
        public static void main(String[] args) throws Exception{
            ResourceClassLoader loader=new ResourceClassLoader();
            loader.resolve("jdkguide.print.PrintfGuide","file:D:\\test\\guide\\target\\classes\\jdkguide\\print\\PrintfGuide.class");
            Class<?> clazz=loader.loadClass("jdkguide.print.PrintfGuide");
            Class.forName("jdkguide.print.PrintfGuide",true,loader);
        }
    }

    @Configuration
    @Import(ConfigLoaderTest.ConfigRegistrar.class)
    public static class Config2{
        @Bean
        public String hello(){
            return "payno";
        }
    }

    @Configuration
    public static class ConfigLoaderTest implements Aware {
        @Bean
        public String hello(){
            return "hello";
        }
        public static void errorTry(AnnotationConfigApplicationContext context) throws Exception{
            ResourceClassLoader loader=new ResourceClassLoader();
            loader.resolve("jdkguide.print.PrintfGuide","file:D:\\test\\guide\\target\\classes\\jdkguide\\print\\PrintfGuide.class");
            loader.resolve("jdkguide.print.PrintConfig","file:D:\\test\\guide\\target\\classes\\jdkguide\\print\\PrintConfig.class");
            loader.loadClass("jdkguide.print.PrintfGuide");
            Class<?> clazz=loader.loadClass("jdkguide.print.PrintConfig");
            context.register(clazz);
            /*Method method=Class.forName("org.springframework.context.support.PostProcessorRegistrationDelegate")
                    .getDeclaredMethod("invokeBeanFactoryPostProcessors",ConfigurableListableBeanFactory.class,List.class);
            method.setAccessible(true);
            method.invoke(null,context,context.getBeanFactoryPostProcessors());*/
        }

        public static void tryWithEnable(){
            /**
             * 使用Enable注解和扩展ImportSelect接口实现注入
             */
        }

        public static class ConfigRegistrar implements ImportBeanDefinitionRegistrar{
            @Override
            public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
                try{
                    ResourceClassLoader loader=new ResourceClassLoader();
                    loader.resolve("jdkguide.print.PrintfGuide","file:D:\\test\\guide\\target\\classes\\jdkguide\\print\\PrintfGuide.class");
                    loader.resolve("jdkguide.print.PrintConfig","file:D:\\test\\guide\\target\\classes\\jdkguide\\print\\PrintConfig.class");
                    loader.loadClass("jdkguide.print.PrintfGuide");
                    Class<?> clazz=loader.loadClass("jdkguide.print.PrintConfig");
                    BeanDefinitionBuilder printConfig=BeanDefinitionBuilder.rootBeanDefinition(clazz);
                    registry.registerBeanDefinition("printConfig",printConfig.getBeanDefinition());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args) throws Exception{
            AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ConfigLoaderTest.class);
            errorTry(context);
            //context.register(Config2.class);这个方法不会注入
            ImmutableList.copyOf(context.getBeanDefinitionNames()).forEach(System.out::println);
            //PrintfGuide 没有注解会注入,只会注入本身，但是相关的配置并不会注入
            context.getBeanFactoryPostProcessors().forEach(beanFactoryPostProcessor -> {
                beanFactoryPostProcessor.postProcessBeanFactory(context.getDefaultListableBeanFactory());
            });
            context.registerBean("abc",String.class,"abcd");
            System.out.println(context.getBean("abc"));
        }
    }
    @lombok.Data
    @AllArgsConstructor
    public static class Data{
        private String name;
    }
    @Configuration
    @lombok.Data
    public static class Config{
        @Autowired
        Data var;
    }
    @Configuration
    public static class AutowiredAnnotationProcessorTest{
        @Bean
        public Data data(){
            return new Data("ABC");
        }

        @Bean
        public Joiner joiner(){
            return Joiner.on("|");
        }

        public static void main(String[] args) throws Exception{
            AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AutowiredAnnotationProcessorTest.class);
            context.register(Config.class);
            System.out.println(context.getBean(Config.class));
            ResourceClassLoader loader=new ResourceClassLoader(ClassUtils.getDefaultClassLoader());
            loader.resolve("jdkguide.print.PrintfGuide","file:D:\\test\\guide\\target\\classes\\jdkguide\\print\\PrintfGuide.class");
            loader.resolve("jdkguide.print.PrintConfig","file:D:\\test\\guide\\target\\classes\\jdkguide\\print\\PrintConfig.class");
            loader.loadClass("jdkguide.print.PrintfGuide");
            loader.loadClass("com.google.common.base.Joiner");
            Class<?> clazz=loader.loadClass("jdkguide.print.PrintConfig");
            context.register(clazz);
            System.out.println(context.getBean(clazz));
            ImmutableList.copyOf(clazz.getDeclaredMethods()).forEach(System.out::println);
            ImmutableList.copyOf(clazz.getDeclaredFields()).forEach(System.out::println);
            //Joiner joiner=(Joiner) clazz.getDeclaredField("joiner").get(context.getBean(clazz));
            //System.out.println(joiner.join("a","b"));
        }
    }
}

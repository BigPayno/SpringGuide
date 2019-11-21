package com.payno.springguide.spring;

import com.google.common.collect.ImmutableList;
import lombok.Data;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author payno
 * @date 2019/11/18 11:25
 * @description
 *      @CompomemtScam  扫描配置
 *      TypeFilter接口  @Filter
 *      环境版本        @Profile    Spring3
 *      按条件注入      @Conditional     Spring4
 *      以及扩展接口ConditionOnMissing等
 *
 */
@SpringBootApplication
@ComponentScan(value = "com.payno.springguide",
        includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Component.class,Service.class})
        },
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = BootEnvironmentGuide.CustomFilter.class)
        }
)
public class BootEnvironmentGuide {
    public static class CustomFilter implements TypeFilter {
        @Override
        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
            // 获取当前正在扫描的类的注解信息
            AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
            // 获取当前正在扫描的类的类信息
            ClassMetadata classMetadata = metadataReader.getClassMetadata();
            // 获取当前正在扫描的类的路径等信息
            Resource resource = metadataReader.getResource();
            String className = classMetadata.getClassName();
            return StringUtils.hasText("er");
        }
    }
    public static class ConditionOn implements Condition{
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            boolean missing=!conditionContext.getEnvironment().containsProperty("user");
            BeanFactory beanFactory=conditionContext.getBeanFactory();
            boolean missingUser=!conditionContext.getBeanFactory().containsBean("user");
            System.out.println("jdk7 环境下必须有user类，正在注入");
            return missing&&missingUser;
        }
    }
    @Configuration
    public static class Config{
        @Service
        @Profile("jdk7")
        public static class Java7{
            public void version(){
                System.out.println("run with jdk7!");
            }
        }
        @Service
        @Profile("jdk7")
        public static class Java7er{
            public void version(){
                System.out.println("run with jdk7");
            }
        }
        @Service
        @Profile("jdk8")
        public static class Java8{
            public void version(){
                System.out.println("run with jdk8");
            }
        }
    }
    @Conditional({ConditionOn.class})
    @Component
    @Data
    public static class User{
        String userName;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context=new SpringApplicationBuilder(BootEnvironmentGuide.class)
                .web(WebApplicationType.NONE)
                .profiles("jdk7")
                .run(args);
        ImmutableList.copyOf(context.getBeanDefinitionNames()).stream().filter(str->str.startsWith("com.payno")).forEach(System.out::println);
    }
}

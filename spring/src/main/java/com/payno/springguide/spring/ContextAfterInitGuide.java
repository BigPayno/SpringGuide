package com.payno.springguide.spring;

import com.google.common.collect.ImmutableList;
import lombok.*;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2019/11/23 13:27
 * @description
 */
public class ContextAfterInitGuide {
    @Configuration
    @Import(Hello.class)
    public static class AfterConfig{
        @Component
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Person{
            private String name;
            public void print(){
                System.out.println(name);
            }
        }
        @Setter
        @Getter
        @Component
        public static class Config{
            @Autowired
            public Person person;
        }

        @Setter
        @Getter
        @Component
        @DependsOn("")
        public static class Config2{
            @Autowired
            public Person person;
        }

        public static void main(String[] args) {
            AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AfterConfig.class);
            /**
             * 测试改动之前
             */
            ImmutableList.copyOf(context.getBeanDefinitionNames()).stream()
                    .filter(str->str.startsWith("com.payno"))
                    .forEach(System.out::println);
            context.getBean(Config.class).getPerson().print();
            Person person=new Person("payno");
            /**
             * 改之后，确实config对应的person变了,
             * 但是Context里的person没变
             */
            context.getBean(Config.class).setPerson(person);
            context.getBean(Config.class).getPerson().print();
            context.getBean(Person.class).print();

            /**
             * 尝试删除BeanDefinition
             */
           //context.removeBeanDefinition("com.payno.springguide.config.ContextAfterInitGuide$AfterConfig$Person");
            Config2 config2=context.getBean(Config2.class);
            /*BeanDefinition beanDefinition=new AnnotatedGenericBeanDefinition(Person.class);
            context.registerBeanDefinition(
                    "com.payno.springguide.config.ContextAfterInitGuide$AfterConfig$Person",
                     beanDefinition
            );*/
            config2.getPerson().print();
            /**
             * 得到BeanDefinitions
             */
            ImmutableList.copyOf(context.getBeanDefinitionNames()).stream()
                    .filter(name->name.startsWith("com.payno"))
                    .map(name->context.getBeanDefinition(name))
                    .forEach(beanDefinition -> {
                        if(beanDefinition.getDependsOn()!=null){
                            ImmutableList.copyOf(beanDefinition.getDependsOn())
                                    .forEach(depend->{
                                        System.out.println(beanDefinition+" Depends on "+depend);
                                    });
                        }
                    });
        }
    }
}

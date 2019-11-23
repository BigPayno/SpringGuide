package com.payno.springguide.spring;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2019/11/23 13:02
 * @description
 *      Spring加载Bean默认通过Bean的构造器
 *      如果有Factory，每次调用都通过Factory获得
 *      如果是单例的，则每次获得的是一样的
 */
public class FactoryBeanGuide {
    @Configuration
    public static class FactoryContext{
        //@Component
        /**
         * 如果有这个注入，这个类的注入不是通过Factory调产生
         * 还是靠构造器
         */
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Person{
            private String name;
            public void print(){
                System.out.println(name);
            }
        }

        @Component
        public static class UsePerson{
            @Autowired
            Person person1;
            @Autowired
            Person person2;
            public void print(){
                person1.print();
                person2.print();
            }

            public void test(){
                System.out.println(person1==person2);
            }
        }

        @Component
        public static class PersonFactory implements FactoryBean<Person>{
            @Override
            public boolean isSingleton() {
                return false;
            }

            @Override
            public Person getObject() throws Exception {
                return new Person("payno");
            }

            @Override
            public Class<?> getObjectType() {
                return Person.class;
            }
        }

        public static void main(String[] args) {
            AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(FactoryContext.class);
            context.getBean(UsePerson.class).print();
            context.getBean(UsePerson.class).test();
            ImmutableList.copyOf(context.getBeanDefinitionNames()).stream()
                    .filter(str->str.startsWith("com.payno"))
                    .forEach(System.out::println);
            context.getBeanFactory().getBeansOfType(Person.class)
                    .forEach((k,v)->{
                        System.out.println(k+":"+v);
                    });
        }
    }
}

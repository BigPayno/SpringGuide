package com.payno.springguide.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.ref.Reference;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * @author payno
 * @date 2019/11/23 14:52
 * @description
 *      spring初期注册BeanDefinition，最后通过bd常见Instance
 *      创建之后就不关Spring管理了
 *      通过getBean方法是强耦合于Spring的
 */
public class GetBeanGuide {
    @Configuration
    public static class Test{
        @Component
        @Scope(value = SCOPE_PROTOTYPE)
        public static class O{
            public void hash(){
                System.out.println(this.hashCode());
            }
        }
        @Component
        public static class A{
            @Autowired
            O o1;
            @Lookup
            public O getO(){
                return null;
            }
            public void hash(){
                System.out.println(this.hashCode());
            }
        }

        @Component
        public static class B{

        }

        public static void main(String[] args) {
            AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Test.class);
            A a1=context.getBean(A.class);
            A a2=context.getBean(A.class);
            B b1=context.getBean(B.class);
            a1.hash();a2.hash();
            /**
             * @Autowired 单例注入
             * @Lookup 原型注入
             */
            a1.o1.hash();a2.o1.hash();
            a1.getO().hash();a2.getO().hash();
        }
    }
}

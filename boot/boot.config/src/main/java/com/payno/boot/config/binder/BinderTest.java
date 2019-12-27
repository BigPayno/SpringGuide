package com.payno.boot.config.binder;

import com.payno.boot.config.BootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author payno
 * @date 2019/12/27 10:47
 * @description
 *      SpringBoot2.x 新增特性Binder
 *          更高级的进行Config绑定的Api
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class BinderTest {

    @Autowired
    Environment environment;

    @Test
    public void bindObject(){
        /**
         * 绑定普通对象
         */
        Binder.get(environment)
                .bind("binder.object.binder-user", Bindable.of(BinderUser.class))
                .ifBound(System.out::println);

        /**
         * 绑定泛型对象
         */
        ResolvableType bindType=ResolvableType.forClassWithGenerics(BinderGeneric.class,BinderUser.class);
        BinderGeneric<BinderUser> generic=(BinderGeneric<BinderUser>)Binder.get(environment)
                .bind(
                        "binder.object.binder-generic",
                        Bindable.of(bindType)
                ).get();
        System.out.println(generic);

        /**
         * 绑定值，普通对象
         *     Class<T> type = instance.getClass()
         *     return of(type).withExistingValue(instance)
         */
        BinderUser binderUser=new BinderUser();
        Binder.get(environment)
                .bind("binder.object.binder-user",Bindable.ofInstance(binderUser));
        System.out.println(binderUser);

        /**
         * 绑定值，泛型对象,使用withExistingValue绑定已有对象
         */
        BinderGeneric<BinderUser> binderGeneric=new BinderGeneric<>();
        ResolvableType type=ResolvableType.forClassWithGenerics(BinderGeneric.class,BinderUser.class);
        Binder.get(environment)
                .bind("binder.object.binder-generic",Bindable.of(type).withExistingValue(binderGeneric))
                .get();
        System.out.println(binderGeneric);

        /**
         * 查看是否可以基于ResolvedType.forInstance
         * 来构建自由的绑定
         */
        BinderGeneric<String> binderGeneric2=new BinderGeneric<>();
        Binder.get(environment)
                .bind(
                        "binder.object.binder-generic2",
                        Bindable.of(ResolvableType.forInstance(binderGeneric2)).withExistingValue(binderGeneric2)
                );
        System.out.println(binderGeneric2);
    }

    @Test
    public void bindCollection(){
        Binder.get(environment)
                .bind("binder.collection.list",Bindable.listOf(String.class))
                .ifBound(System.out::println);
        Binder.get(environment)
                .bind("binder.collection.set",Bindable.setOf(Integer.class))
                .ifBound(System.out::println);
        Binder.get(environment)
                .bind("binder.collection.map",Bindable.mapOf(Character.TYPE,Integer.TYPE))
                .ifBound(System.out::println);
    }

    @Test
    public void bindGenericCollection(){
        ResolvableType resolvableType=ResolvableType.forClass(BindGenericCollection.class);
        Binder.get(environment)
                .bind(
                        "binder.object.binder-generic-collection",
                        Bindable.of(resolvableType)
                ).ifBound(System.out::println);
    }

    @Test
    public void bindCallback(){
        BinderUser user=Binder.get(environment)
                .bind("binder.object",Bindable.of(BinderUser.class))
                .orElse(new BinderUser("chad","payno"));
        System.out.println(user);
    }
}

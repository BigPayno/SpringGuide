package com.payno.springguide.spring.enable.bootstrap;

import com.google.common.collect.ImmutableList;
import com.payno.springguide.spring.enable.autoconfig.EnableHelloWorld;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author payno
 * @date 2019/11/22 08:45
 * @description
 *      @ConditionalOnClass ： classpath中存在该类时起效
 *      @ConditionalOnMissingClass ： classpath中不存在该类时起效
 *      @ConditionalOnBean ： DI容器中存在该类型Bean时起效
 *      @ConditionalOnMissingBean ： DI容器中不存在该类型Bean时起效
 *      @ConditionalOnSingleCandidate ： DI容器中该类型Bean只有一个或@Primary的只有一个时起效
 *      @ConditionalOnExpression ： SpEL表达式结果为true时
 *      @ConditionalOnProperty ： 参数设置或者值一致时起效
 *      @ConditionalOnResource ： 指定的文件存在时起效
 *      @ConditionalOnJndi ： 指定的JNDI存在时起效
 *      @ConditionalOnJava ： 指定的Java版本存在时起效
 *      @ConditionalOnWebApplication ： Web应用环境下起效
 *      @ConditionalOnNotWebApplication ： 非Web应用环境下起效
 */
@EnableHelloWorld
@SpringBootApplication
public class BootEnableGuide {
    public static void main(String[] args) {
        ConfigurableApplicationContext context=new SpringApplicationBuilder(BootEnableGuide.class)
                .web(WebApplicationType.NONE)
                .run(args);
        ImmutableList.copyOf(context.getBeanDefinitionNames()).stream().filter(str->str.startsWith("com.payno")).forEach(System.out::println);
        context.getBean(People.class).print();
    }
}

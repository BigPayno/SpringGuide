package com.payno.boot.config.env;

import com.payno.boot.config.binder.BinderUser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.*;
import org.springframework.core.io.ClassPathResource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author payno
 * @date 2019/12/27 16:28
 * @description
 */
public class PropertyResolverTest {
    MutablePropertySources mutablePropertySources;
    @Before
    public void init() throws Exception{
        YamlPropertySourceLoader loader=new YamlPropertySourceLoader();
        List<PropertySource<?>> propertySources=loader.load("yml",new ClassPathResource("application.yml"));
        mutablePropertySources=new MutablePropertySources();
        for(PropertySource yml:propertySources){
            mutablePropertySources.addLast(yml);
        }
    }

    @Test
    public void resolver(){
        mutablePropertySources.forEach(propertySource -> {
            System.out.println(propertySource.getProperty("binder.object.binder-user.name"));
        });
        PropertyResolver resolver=new PropertySourcesPropertyResolver(mutablePropertySources);
        /**
         * 多了解析成对应Type的能力
         */
        String name=resolver.getProperty("binder.object.binder-user.name",String.class);
        System.out.println(name);

        /**
         * SomethingWrong
         */
        ConfigurablePropertyResolver propertyResolver=(ConfigurablePropertyResolver)resolver;
        propertyResolver.setConversionService(new DefaultConversionService());
        System.out.println(propertyResolver.getConversionService());
        propertyResolver.getConversionService().addConverter(
                String.class,LocalDate.class,(Converter<String, LocalDate>)  str-> LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        LocalDate localDate=propertyResolver.getProperty("date",LocalDate.class);
        System.out.println(localDate);
    }

    @Test
    public void env(){
        /**
         * 主要有两点
         * 1.拥有使用Spring的ConverterService的能力
         * 2.提供了不同的Profile
         * StandardEnvironment：标准环境，普通Java应用时使用，会自动注册System.getProperties() 和 System.getenv()到环境；
         *
         * StandardServletEnvironment：标准Servlet环境，其继承了StandardEnvironment，Web应用时使用，除了StandardEnviron
         * ment外，会自动注册ServletConfig（DispatcherServlet）、ServletContext及JNDI实例到环境；
         */
        StandardEnvironment environment=new StandardEnvironment();
        mutablePropertySources
                .forEach(propertySource -> environment.getPropertySources().addLast(propertySource));
        BinderUser binderUser=new BinderUser();
        Binder.get(environment).bind(
                "binder.object.binder-user",
                Bindable.of(ResolvableType.forInstance(binderUser)).withExistingValue(binderUser)
        ).ifBound(System.out::println);
    }
}

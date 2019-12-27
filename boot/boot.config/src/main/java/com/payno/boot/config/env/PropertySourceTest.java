package com.payno.boot.config.env;

import org.junit.Test;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.boot.json.YamlJsonParser;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author payno
 * @date 2019/12/27 16:04
 * @description
 */
public class PropertySourceTest {
    @Test
    public void sources() throws Exception{
        /**
         * Map
         */
        Map<String,Object> map= Collections.singletonMap("payno","chad");
        PropertySource<Map<String,Object>> propertySource=new MapPropertySource("map",map);
        System.out.println(propertySource.getProperty("payno"));

        /**
         * Properties
         */
        PropertySource<Map<String,Object>> propertySource1=new ResourcePropertySource("property","classpath:properties/hello.properties");
        System.out.println(propertySource1.getProperty("hello"));

        /**
         * Yml,虚假的yml只支持简单
         */
        PropertySource<Map<String,Object>> propertySource2=new ResourcePropertySource("yml","classpath:properties/hello.yml");
        System.out.println(propertySource2.getProperty("hello"));

        /**
         * 真实的yml,通过以下两者获得
         * YamlPropertiesFactoryBean
         * YamlPropertySourceLoader
         */
        YamlPropertySourceLoader loader=new YamlPropertySourceLoader();
        List<PropertySource<?>> propertySources=loader.load("yml",new ClassPathResource("application.yml"));
        MutablePropertySources ymlSources=new MutablePropertySources();
        for(PropertySource yml:propertySources){
            ymlSources.addLast(yml);
        }

        /**
         * Source Collection
         */
        MutablePropertySources mutablePropertySources=new MutablePropertySources();
        mutablePropertySources.addLast(propertySource);
        mutablePropertySources.addLast(propertySource1);
        mutablePropertySources.addLast(propertySource2);
        for(PropertySource childSource:mutablePropertySources){
            System.out.println(childSource.getProperty("hello"));
        }
    }
}

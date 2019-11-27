package com.payno.webmvc.web.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @author payno
 * @date 2019/11/20 17:28
 * @description
 */
@Configuration
public class MultipartConfig {
    /**
     * 配置Multipart默认上传路径
     */
    @Bean
    MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory=new MultipartConfigFactory();
        /**
         * 相对jar的路径  / 当前路径
         */
        factory.setLocation("/");
        return factory.createMultipartConfig();
    }
}

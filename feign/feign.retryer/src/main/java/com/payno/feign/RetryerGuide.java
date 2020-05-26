package com.payno.feign;

import feign.Feign;
import feign.Response;
import feign.RetryableException;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import feign.codec.StringDecoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author payno
 * @date 2020/5/26 16:30
 * @description
 */
public class RetryerGuide {

    /**
     *  重试
     *
     *  Feign默认会自动重试所有抛出了IOException的方法，也会自动重试ErrorDecoder抛出的RetryableException请求。
     *  可以通过builder注册自定义的Retryer来实现自定义的重试策略。
     *
     *
     *  Retryer根据方法continueOrPropagate(RetryableException e)返回true 或者false来决定是否进行重试，
     *  Retryer在每个Client创建时指定，可以根据不同的场景使用特定的重试策略。
     *  如果重试失败最终的抛出一个RetryException异常。可以通过为Feign client配置exceptionPropagationPolicy()选项，
     *  会输出请求失败的原始异常信息。
     */

    public interface EasyClient {
        @PostMapping("/error")
        void error();

        @PostMapping("/error2")
        void error2();
    }

    public static void main(String[] args) {
        EasyClient client = Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new SpringEncoder(()->
                        new HttpMessageConverters(new FormHttpMessageConverter(),new StringHttpMessageConverter())))
                .decoder(new StringDecoder())
                .retryer(new Retryer.Default())
                .target(EasyClient.class,"http://localhost:8080");
        client.error();
    }
}

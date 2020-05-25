package com.payno.feign;

import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.StringDecoder;
import feign.gson.GsonEncoder;
import feign.spring.SpringContract;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author payno
 * @date 2020/5/25 11:48
 * @description
 */
public class RequestInterceporDemo {
    public interface EasyClient {
        @PostMapping("/hello/{name}")
        String get(@PathVariable("name") String name);
    }

    public static void main(String[] args) {
        EasyClient client = Feign.builder()
                .contract(new SpringContract())
                .encoder(new GsonEncoder())
                .decoder(new StringDecoder())
                .requestInterceptor(request->{
                    /**
                     *  类似RestTemplate的拦截器
                     */
                        request.header("auth","test");
                })
                .target(EasyClient.class,"http://localhost:8080");
        System.out.println(client.get("payno"));
    }
}

package com.payno.feign;

import feign.Feign;
import feign.codec.StringDecoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.spring.SpringContract;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author payno
 * @date 2020/5/25 11:34
 * @description
 */
public class SpringContractDemo {
    public interface EasyClient {
        @PostMapping("/hello/{name}")
        String get(@PathVariable("name") String name);
    }

    public static void main(String[] args) {
        EasyClient client = Feign.builder()
                .contract(new SpringContract())
                .encoder(new GsonEncoder())
                .decoder(new StringDecoder())
                .target(EasyClient.class,"http://localhost:8080");
        System.out.println(client.get("payno"));
    }
}

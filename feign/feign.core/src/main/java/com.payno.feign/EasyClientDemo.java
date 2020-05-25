package com.payno.feign;

import feign.Contract;
import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.codec.StringDecoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

/**
 * @author payno
 * @date 2020/5/25 11:21
 * @description
 */
public class EasyClientDemo {
    public interface EasyClient {
        @RequestLine("POST /hello/{name}")
        String get(@Param("name") String name);
    }

    public static void main(String[] args) {
        EasyClient client = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new StringDecoder())
                .target(EasyClient.class,"http://localhost:8080");
        System.out.println(client.get("payno"));
    }
}

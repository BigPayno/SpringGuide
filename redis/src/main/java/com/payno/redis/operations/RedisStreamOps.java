package com.payno.redis.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.PostConstruct;

/**
 * @author payno
 * @date 2019/12/1 21:09
 * @description
 *      支持多播的可持久化的消息队列
 *      x开头
 */
public class RedisStreamOps {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    StreamOperations<String,String,String> streamOps;
    @PostConstruct
    private void initOps(){
        streamOps=stringRedisTemplate.opsForStream();
    }

    public void ops(){

    }
}

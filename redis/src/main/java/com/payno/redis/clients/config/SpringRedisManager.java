package com.payno.redis.clients.config;

import lombok.extern.slf4j.Slf4j;
import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.serializer.ObjectSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author payno
 * @date 2020/5/27 17:27
 * @description
 */

@Slf4j
@Component
public class SpringRedisManager implements IRedisManager {

    //@Autowired
    RedisConnectionFactory connectionFactory;

    RedisTemplate<byte[],byte[]> template;

    ObjectSerializer objectSerializer = new ObjectSerializer();

    @PostConstruct
    public void initTemplate(){
        if(connectionFactory!=null){
            template =new RedisTemplate<>();
            template.setConnectionFactory(connectionFactory);
            template.setKeySerializer(ByteArrayRedisSerializer.INSTANCE);
            template.setValueSerializer(ByteArrayRedisSerializer.INSTANCE);
            template.setDefaultSerializer(ByteArrayRedisSerializer.INSTANCE);
            template.afterPropertiesSet();
        }
    }

    @Override
    public byte[] get(byte[] bytes) {
        byte[] bytes1 =template.opsForValue().get(bytes);
        if(log.isDebugEnabled()){
            Object val = null;
            try{
                val = objectSerializer.deserialize(bytes1);
            }catch (Exception e){
                e.printStackTrace();
            }
            log.debug("get {} {}",new String(bytes),val);
        }
        return bytes1;
    }

    @Override
    public byte[] set(byte[] bytes, byte[] bytes1, int i) {
        if(log.isDebugEnabled()){
            Object val = null;
            try{
                val = objectSerializer.deserialize(bytes1);
            }catch (Exception e){
                e.printStackTrace();
            }
            log.debug("set {} {}",new String(bytes),val);
        }
        template.opsForValue().set(bytes,bytes1,i, TimeUnit.SECONDS);
        return bytes1;
    }

    @Override
    public void del(byte[] bytes) {
        if(log.isDebugEnabled()){
            log.debug("del {}",new String(bytes));
        }
        template.delete(bytes);
    }

    @Override
    public Long dbSize(byte[] bytes) {
        if(log.isDebugEnabled()){
            log.debug("dbSize {}",new String(bytes));
        }
        return template.execute((RedisCallback<Long>)
                redisConnection -> redisConnection.dbSize());
    }

    @Override
    public Set<byte[]> keys(byte[] bytes) {
        if(log.isDebugEnabled()){
            log.debug("keys {}",new String(bytes));
        }
        return template.keys(bytes);
    }
}
package com.payno.redis.clients.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author payno
 * @date 2019/12/1 15:55
 * @description
 *
 */
@Configuration
public class RedisConfiguration {
    private static final RedisSerializer<String> STRING_REDIS_SERIALIZER = new StringRedisSerializer();
    private static final RedisSerializer<Object> JACKSON_REDIS_SERIALIZER = new GenericJackson2JsonRedisSerializer();
    private static final RedisSerializer<Object> FAST_JSON_REDIS_SERIALIZER = new FastJsonRedisSerializer<>(Object.class);

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        //设置后会出现一个hash:id:plantom
        template.setEnableTransactionSupport(false);
        template.setKeySerializer(STRING_REDIS_SERIALIZER);
        template.setValueSerializer(FAST_JSON_REDIS_SERIALIZER);
        template.setHashKeySerializer(STRING_REDIS_SERIALIZER);
        template.setHashValueSerializer(JACKSON_REDIS_SERIALIZER);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(connectionFactory);
        stringRedisTemplate.setKeySerializer(STRING_REDIS_SERIALIZER);
        stringRedisTemplate.setValueSerializer(STRING_REDIS_SERIALIZER);
        return stringRedisTemplate;
    }
}

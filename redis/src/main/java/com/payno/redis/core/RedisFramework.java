package com.payno.redis.core;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author payno
 * @date 2019/12/20 16:03
 * @description
 */
public class RedisFramework {
    /**
     * RedisTemplate是模板类
     * 1.内置序列化器
     *      处理Java[String]转换成二进制数组的序列化方式，其中使用StringSerializer的拥有将String序列化
     *  到Redis的能力（换而言之，并不能对非String类型进行序列化）
     * StringRedisTemplate，
     * 2.Lua脚本执行器
     * 3.常见数据结构、客户端集群结点操作类
     * 4.执行方法
     * 5.连接工厂（多种 Java Redis 客户端,类似RestTemplate）
     */
    public void serializer(){
        RedisTemplate<String,Object> template=new RedisTemplate<>();
        template.setConnectionFactory(new JedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericFastJsonRedisSerializer());
    }
}

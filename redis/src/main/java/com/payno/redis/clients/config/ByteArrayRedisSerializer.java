package com.payno.redis.clients.config;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author payno
 * @date 2020/5/28 10:10
 * @description
 */
public enum ByteArrayRedisSerializer implements RedisSerializer<byte[]> {

    INSTANCE;

    @Override
    public byte[] serialize(byte[] bytes) throws SerializationException {
        return bytes;
    }

    @Override
    public byte[] deserialize(byte[] bytes) throws SerializationException {
        return bytes;
    }
}

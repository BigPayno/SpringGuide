package com.payno.redis.executes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.List;

/**
 * @author payno
 * @date 2019/12/1 21:21
 * @description
 */
public class ExecGuide {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    /**
     * 执行redis操作，SessionCallBack、RedisCallBack、RedisScript
     */
    public void operations(DefaultRedisScript<Long> redisScript, List<String> keys, Object... args){
        stringRedisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                return null;
            }
        });
        stringRedisTemplate.execute((RedisCallback<? extends Object>) connection->{
            return null;
        });
        stringRedisTemplate.execute(redisScript, keys, args);
        /**
         * 管道操作
         */
        stringRedisTemplate.executePipelined((RedisCallback<? extends Object>) connection->{
            return null;
        });
    }

    /**
     * 1.MULTI用来组装一个事务；
     * 2.EXEC用来执行一个事务；
     * 3.DISCARD用来取消一个事务；
     * 4.WATCH类似于乐观锁机制里的版本号。
     */
    public void transactions(){
        stringRedisTemplate.multi();
        stringRedisTemplate.exec();
        stringRedisTemplate.discard();
        stringRedisTemplate.watch("key");
    }
}

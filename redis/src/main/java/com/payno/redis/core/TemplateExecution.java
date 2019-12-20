package com.payno.redis.core;

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
public class TemplateExecution {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    /**
     * 执行redis操作，SessionCallBack、RedisCallBack、RedisScript
     */
    public void operations(DefaultRedisScript<Long> redisScript, List<String> keys, Object... args){
        /**
         * SessionCallback保证在同一次连接中执行,必须tryCatch
         */
        stringRedisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                try{
                    //doSomething
                }catch (Exception e){
                    //catch exception
                }
                return null;
            }
        });
        /**
         * RedisCallback
         * @exposeConnection    是否向RedisCallback暴露本地连接
         * @pipeline        是否在一个管道中执行操作
         */
        stringRedisTemplate.execute((RedisCallback<? extends Object>) connection->{
            return null;
        },true,true);
        /**
         * 通过lua脚本执行redis
         */
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
        /**
         * 实际场景下，如果胖友有 Redis 事务的诉求，建议把事务的、和非事务的 RedisTemplate 拆成两个连接池，相互独立。主要原因有两个：
         *
         *     1）Spring Data Redis 的事务设计，是将其融入到 Spring 整个 Transaction 当中。一般来说，Spring Transaction 中，肯定会存在数据库的 Transaction 。考虑到数据库操作相比 Redis 来说，肯定是慢得多，那么就会导致 Redis 的 Connection 一直被当前 Transaction 占用着。
         *     2）How can i eliminate getting junk value through redis get command?
         */
        stringRedisTemplate.multi();
        stringRedisTemplate.exec();
        stringRedisTemplate.discard();
        stringRedisTemplate.watch("key");
    }
}

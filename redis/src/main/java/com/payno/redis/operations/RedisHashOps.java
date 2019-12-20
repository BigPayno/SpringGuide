package com.payno.redis.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2019/12/1 17:15
 * @description
 *      在redis中，只要key一样就不能再次存同名值
 *      Redis中的Hash类型可以看成具有String key 和String value的map容器。所以该类型非常适合于存储对象的信息。
 *      如Username、password和age。如果Hash中包含少量的字段，那么该类型的数据也将仅占用很少的磁盘空间。
 */
@Profile("jedis")
@Component
public class RedisHashOps {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    /**
     * hset key field value : 为指定的key设定field/value对（键值对）。
     * hmset key field value [field2 value2 ......] : 设置key中多个field/value。
     */
    public void set(){
        HashOperations<String,String,String> hashOps=stringRedisTemplate.opsForHash();
        hashOps.put("user","name","payno");
        hashOps.put("user","pwd","2789");
    }

    /**
     * hget key field : 返回指定key中的field的值
     * hmget key field : 获取key中的多个field的值
     * hgetall key : 获取key中所有的field-value
     */
    public void get(){
        HashOperations<String,String,String> hashOps=stringRedisTemplate.opsForHash();
        hashOps.entries("user").entrySet().forEach(entry->{
            System.out.println(entry.getKey()+" : "+entry.getValue());
        });
    }

    /**
     * del key
     * hdel key field...
     * 会报异常
     * @hashOps.delete("user");
     */
    public void del(){
        HashOperations<String,String,String> hashOps=stringRedisTemplate.opsForHash();
        hashOps.delete("user","name");
        hashOps.entries("user").entrySet().forEach(entry->{
            System.out.println(entry.getKey()+" : "+entry.getValue());
        });
        stringRedisTemplate.delete("user");
    }

    /**
     * 获取hash的field长度，keys，vals
     *  hlen key
     *  hkeys key
     *  hvals key
     *  hincrby key field increment :
     *  设置key中field的值增加increment。
     *  hexists key field ：
     *  判断指定的key中的field是否存在
     */
    public void others(){
        HashOperations<String,String,String> hashOps=stringRedisTemplate.opsForHash();
        hashOps.keys("user").forEach(System.out::println);
        hashOps.values("user").forEach(System.out::println);
        System.out.println(hashOps.size("user"));
    }
}

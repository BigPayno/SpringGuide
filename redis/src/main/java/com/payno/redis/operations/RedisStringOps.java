package com.payno.redis.operations;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.payno.redis.operations.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2019/12/1 16:22
 * @description
 * 定义SDS对象，此对象中包含三个属性：
 *     len buf中已经占有的长度(表示此字符串的实际长度)
 *     free buf中未使用的缓冲区长度
 *     buf[] 实际保存字符串数据的地方
 *
 *     所以取字符串的长度的时间复杂度为O(1)，另，buf[]中依然采用了C语言的以\0结尾可以直接使用C语言的部分标准C字符串库函数。
 *     空间分配原则：当len小于IMB（1024*1024）时增加字符串分配空间大小为原来的2倍，当len大于等于1M时每次分配 额外多分配1M的空间。
 *
 * 由此可以得出以下特性：
 *     redis为字符分配空间的次数是小于等于字符串的长度N，而原C语言中的分配原则必为N。降低了分配次数提高了追加速度，代价就是多占用一些内存空间，且这些空间不会自动释放。
 *     二进制安全的
 *     高效的计算字符串长度(时间复杂度为O(1))
 *     高效的追加字符串操作。
 */
@Component
public class RedisStringOps {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    /**
     *  string ops:
     *   set [key] [value]
     *      设定key持有指定的字符串value，如果该key存在则进行覆盖操作。总是返回OK。
     *   get [key]
     *      获取key的value。如果与该key关联的value不是String类型，redis将返回错误信息，
     *      因为get命令只能用于获取String value；如果key不存在，返回nil。
     *   getset [key] [value]
     *      先获取该key的值，然后再设置该key的值。
     *   del [key]
     *      删除指定key
     *   incr/decr [key]
     *      将指定的key的value原子性的增加1/减少1。如果该key不存在，其初始值为0。
     *      在incr之后其值为1.如果value的值不能转成整型，该操作将执行失败并返回相应的错误信息。
     *   incrby/decrby [key] [value]
     *      原子增加减少值
     *   append [key] [value]
     *      拼接字符串。如果key存在，则在原有的基础上追加该值；如果该key不存在，则重新创建一个key/value。返回值为字符串的长度。
     */
     public void operations(){
         ValueOperations<String,String> valueOps=stringRedisTemplate.opsForValue();
         valueOps.set("string","string");
         ValueOperations<String,Object> objOps=redisTemplate.opsForValue();
         objOps.set("user",new User("payne","payno"));
     }

     public void multi(){
         ValueOperations<String,String> valueOps=stringRedisTemplate.opsForValue();
         valueOps.multiGet(ImmutableList.of("string","user")).forEach(System.out::println);
         ValueOperations<String,Object> objOps=redisTemplate.opsForValue();
         JSONObject jsonObject=(JSONObject)objOps.get("user");
         User user=jsonObject.toJavaObject(User.class);
         System.out.println(user);
     }

     public void toJavaObj(){
         ValueOperations<String,String> valueOps=stringRedisTemplate.opsForValue();
         User user=JSONObject.parseObject(valueOps.get("user"),User.class);
         System.out.println(user);
     }
}

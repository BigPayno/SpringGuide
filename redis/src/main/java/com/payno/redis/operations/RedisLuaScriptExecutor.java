package com.payno.redis.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.List;

/**
 * @author payno
 * @date 2019/12/1 15:57
 * @description
 *      通过lua脚本进行redis的原子操作
 */
public class RedisLuaScriptExecutor {
    @Autowired
    StringRedisTemplate redisTemplate;

    public void invoke(String luaResource,List<String> keys, Object... args) {
        Resource resource = new ClassPathResource(luaResource);
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setResultType(Long.class);
        script.setScriptSource(new ResourceScriptSource(resource));
        redisTemplate.execute(script, keys, args);
    }
}

package com.payno.redis.scripts.delayed.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author payno
 * @date 2019/12/1 15:57
 * @description
 *      通过lua脚本进行redis的原子操作
 */
@Component
public class RedisLuaScriptExecutor {
    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * execute
     * @author: payno
     * @time: 2019/12/2 10:47
     * @description:
     * @param luaResource 脚本资源路径
     * @param keys 脚本的keys
     * @param args 脚本的args
     * @return: void
     */
    public void execute(String luaResource,List<String> keys, Object... args) {
        Resource resource = new ClassPathResource(luaResource);
        DefaultRedisScript<Void> script = new DefaultRedisScript<>();
        script.setResultType(Void.class);
        script.setScriptSource(new ResourceScriptSource(resource));
        redisTemplate.execute(script, keys, args);
    }

    public Long submitWithLong(String luaResource,List<String> keys, Object... args) {
        Resource resource = new ClassPathResource(luaResource);
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setResultType(Long.class);
        script.setScriptSource(new ResourceScriptSource(resource));
        return redisTemplate.execute(script, keys, args);
    }

    @SuppressWarnings("unchecked")
    public List<String> submitWithStringList(String luaResource,List<String> keys, Object... args) {
        Resource resource = new ClassPathResource(luaResource);
        DefaultRedisScript<List> script = new DefaultRedisScript<>();
        script.setResultType(List.class);
        script.setScriptSource(new ResourceScriptSource(resource));
        return (List<String>) redisTemplate.execute(script, keys, args);
    }
}

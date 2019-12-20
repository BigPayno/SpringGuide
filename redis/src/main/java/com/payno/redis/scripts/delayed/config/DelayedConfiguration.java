package com.payno.redis.scripts.delayed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author payno
 * @date 2019/12/2 11:06
 * @description
 */
@Profile("jedis")
@Configuration
public class DelayedConfiguration {
    @Bean
    public ScheduledExecutorService scheduledExecutor(){
        return Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors()+1);
    }
}

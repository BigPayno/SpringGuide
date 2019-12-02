package com.payno.redis;

import com.payno.redis.scripts.delayed.support.ScriptSupport;
import com.payno.redis.scripts.delayed.task.DelayedTask;
import com.payno.redis.scripts.delayed.task.RedisDelayedTask;
import com.payno.redis.scripts.delayed.task.api.TimeScoreStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author payno
 * @date 2019/12/1 16:58
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisScriptTests {
    @Autowired
    TimeScoreStrategy timeScoreStrategy;
    @Autowired
    ScriptSupport scriptSupport;

    @Test
    public void pushJobs(){
        String taskType="limitTask";
        List<RedisDelayedTask> tasks= IntStream.range(0,10).boxed().map(num->
            new RedisDelayedTask(taskType,num.toString(),1, TimeUnit.HOURS,timeScoreStrategy)
        ).collect(Collectors.toList());
      scriptSupport.pushJobs(taskType,tasks);
    }

    @Test
    public void pullJobs(){
        /**
         * 默认分数策略就是ms
         */
        List<DelayedTask> delayedTasks=scriptSupport.pullJobs(
                "limitTask",0,
                Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli() );
        delayedTasks.forEach(System.out::println);
    }

    @Test
    public void pullJobs2(){
        List<DelayedTask> delayedTasks=scriptSupport.pullJobs("limitTask",1,TimeUnit.DAYS);
        delayedTasks.forEach(System.out::println);
    }
}

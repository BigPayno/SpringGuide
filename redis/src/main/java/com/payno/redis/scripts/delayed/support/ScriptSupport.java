package com.payno.redis.scripts.delayed.support;

import com.google.common.collect.Lists;
import com.payno.redis.scripts.delayed.task.DelayedTask;
import com.payno.redis.scripts.delayed.task.RedisDelayedTask;
import com.payno.redis.scripts.delayed.task.api.RedisDelayed;
import com.payno.redis.scripts.delayed.task.api.TimeScoreStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author payno
 * @date 2019/12/2 11:32
 * @description
 */
@Component
public class ScriptSupport {
    @Autowired
    TimeScoreStrategy timeScoreStrategy;
    @Autowired
    RedisLuaScriptExecutor luaScriptExecutor;
    /**
     * LuaScript::pushJobs
     * Keys[queueKey,queueSize]
     * Args[score,member,...]
     */
    public void pushJobs(String taskType,List<? extends RedisDelayed> tasks){
        List<String> scriptArgs=tasks.stream().flatMap(task->{
            return Stream.of(String.valueOf(task.getScore(timeScoreStrategy)),((RedisDelayed) task).getDelayedId());
        }).collect(Collectors.toList());
        List<String> scriptKeys= Lists.newArrayList(taskType,String.valueOf(scriptArgs.size()));
        luaScriptExecutor.execute(LuaScripts.PUSH_JOBS, scriptKeys,scriptArgs.toArray(new Object[0]));
    }
    /**
     * 插入不同类型的任务
     */
    public void pushJobs(List<? extends RedisDelayed> tasks){
        Map<String,List<RedisDelayed>> groupTasks=
                tasks.stream().collect(Collectors.groupingBy(RedisDelayed::getSortedSetKey));
        groupTasks.entrySet().forEach(stringListEntry -> {
            pushJobs(stringListEntry.getKey(),stringListEntry.getValue());
        });
    }

    public List<DelayedTask> pullJobs(String taskType, double start, double end){
        List<String> rowModels = luaScriptExecutor.submitWithStringList(
                LuaScripts.PULL_JOBS, Collections.singletonList(taskType),String.valueOf(start),String.valueOf(end));
        return Lists.partition(rowModels,2).stream().map(list->{
            return RedisDelayedTask.of(
                    taskType,list.get(0),list.get(1),timeScoreStrategy
            );
        }).collect(Collectors.toList());
    }

    public List<DelayedTask> pullJobs(String taskType,long duration,TimeUnit timeUnit){
        long nano=System.nanoTime()+ timeUnit.toNanos(duration);
        double end=timeScoreStrategy.toScore(nano);
        return pullJobs(taskType,0,end);
    }
}

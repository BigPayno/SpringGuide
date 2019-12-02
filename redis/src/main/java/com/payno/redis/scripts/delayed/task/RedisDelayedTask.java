package com.payno.redis.scripts.delayed.task;

import com.payno.redis.scripts.delayed.task.api.RedisDelayed;
import com.payno.redis.scripts.delayed.task.api.TimeScoreStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * @author payno
 * @date 2019/12/2 10:51
 * @description
 */
@Getter
@AllArgsConstructor
public class RedisDelayedTask extends DelayedTask implements RedisDelayed {
    private TimeScoreStrategy scoreStrategy;
    public RedisDelayedTask(String taskType, String taskId, long duration, TimeUnit timeUnit,TimeScoreStrategy scoreStrategy) {
        super(taskType, taskId, duration, timeUnit);
        this.scoreStrategy=scoreStrategy;
    }

    @Override
    public String getSortedSetKey() {
        return taskType;
    }

    @Override
    public double getScore(TimeScoreStrategy timeScoreStrategy) {
        return timeScoreStrategy.toScore(executeNanoTime);
    }

    @Override
    public String getDelayedId() {
        return getTaskId();
    }

    @Override
    public void buildExecuteNanoTime(TimeScoreStrategy timeScoreStrategy,double score) {
        executeNanoTime=timeScoreStrategy.toNanoTime(score);
    }

    public static DelayedTask of(String taskType,String taskId,String score,TimeScoreStrategy scoreStrategy){
        long nanoTime=scoreStrategy.toNanoTime(Double.parseDouble(score));
        return new DelayedTask(taskType,taskId,nanoTime);
    }
}

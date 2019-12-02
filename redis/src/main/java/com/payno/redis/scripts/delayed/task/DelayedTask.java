package com.payno.redis.scripts.delayed.task;

import com.google.common.collect.Ordering;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Comparator;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author payno
 * @date 2019/12/2 16:05
 * @description
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DelayedTask implements Delayed {
    protected String taskType;
    protected String taskId;
    protected long executeNanoTime;
    public DelayedTask(String taskType, String taskId, long duration, TimeUnit timeUnit){
        this.taskType=taskType;
        this.taskId=taskId;
        this.executeNanoTime=System.nanoTime()+timeUnit.toNanos(duration);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(executeNanoTime-System.nanoTime(),TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if(o instanceof DelayedTask){
            DelayedTask delayTask=(DelayedTask) o;
            return Ordering.from(Comparator.comparing(DelayedTask::getExecuteNanoTime))
                    .thenComparing(DelayedTask::getTaskId)
                    .compare(this,delayTask);
        }else {
            return Integer.MAX_VALUE;
        }
    }
}

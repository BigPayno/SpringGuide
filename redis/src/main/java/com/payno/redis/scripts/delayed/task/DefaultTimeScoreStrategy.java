package com.payno.redis.scripts.delayed.task;

import com.payno.redis.scripts.delayed.task.api.TimeScoreStrategy;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author payno
 * @date 2019/12/2 16:32
 * @description
 */
@Component
public class DefaultTimeScoreStrategy implements TimeScoreStrategy {
    @Override
    public double toScore(long nanoTime) {
        return TimeUnit.NANOSECONDS.toMillis(nanoTime);
    }

    @Override
    public long toNanoTime(double score) {
        return TimeUnit.MILLISECONDS.toNanos((long) score);
    }
}

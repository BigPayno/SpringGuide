package com.payno.redis.scripts.delayed.task.api;

/**
 * @author payno
 * @date 2019/12/2 16:22
 * @description 标志接口
 */
public interface TimeScoreStrategy {
    double toScore(long nanoTime);
    long toNanoTime(double score);
}

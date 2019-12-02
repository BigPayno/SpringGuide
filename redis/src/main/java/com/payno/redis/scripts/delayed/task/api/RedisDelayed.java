package com.payno.redis.scripts.delayed.task.api;

/**
 * @author payno
 * @date 2019/12/2 10:54
 * @description
 */
public interface RedisDelayed{
    /**
     * getSortedSetKey
     * @author: payno
     * @time: 2019/12/2 10:56
     * @description:
     * @param
     * @return: java.lang.String
     */
    public String getSortedSetKey();
    /**
     * getDelayedId
     * @author: payno
     * @time: 2019/12/2 16:29
     * @description:
     * @param
     * @return: java.lang.String
     */
    public String getDelayedId();
    /**
     * getScore
     * @author: payno
     * @time: 2019/12/2 16:22
     * @description:
     * @param timeScoreStrategy
     * @return: double
     */
    public double getScore(TimeScoreStrategy timeScoreStrategy);
    /**
     * getExecuteNanoTime
     * @author: payno
     * @time: 2019/12/2 16:23
     * @description:
     * @param timeScoreStrategy
     * @return: void
     */
    public void buildExecuteNanoTime(TimeScoreStrategy timeScoreStrategy,double score);
}

package com.payno.redis.scripts.delayed.support;

/**
 * @author payno
 * @date 2019/12/2 11:43
 * @description
 */
public final class LuaScripts {
    public static final String PUSH_JOBS="lua/pushJobs.lua";
    public static final String PULL_JOBS="lua/pullJobs.lua";
}

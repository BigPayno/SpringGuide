package com.payno.task.utils;

/**
 * @author payno
 * @date 2020/3/24 09:23
 * @description
 */
public final class Threads {
    public static void sleep(long millis){
        try{
            Thread.sleep(millis);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

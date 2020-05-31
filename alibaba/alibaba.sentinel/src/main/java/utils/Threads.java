package utils;

import com.google.common.base.Stopwatch;

/**
 * @author payno
 * @date 2020/5/29 10:10
 * @description
 */
public class Threads {
    public static void sleep(long ms){
        try{
            Thread.sleep(ms);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void cost(Runnable runnable){
        Stopwatch stopwatch = Stopwatch.createStarted();
        runnable.run();
        System.out.println(stopwatch.stop());
    }

    public static class PrintStream{
        public static class Out{
            public static void println(String val){
                System.out.println(
                        String.format("%s[Thread] %s",Thread.currentThread().getName(),val));
            }
        }
        public static class Err{
            public static void println(String val){
                System.err.println(
                        String.format("%s[Thread] %s",Thread.currentThread().getName(),val));
            }
        }
    }
}

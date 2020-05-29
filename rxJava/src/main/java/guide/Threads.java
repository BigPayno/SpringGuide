package guide;

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
}

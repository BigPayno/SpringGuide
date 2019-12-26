package recipes;

import client.Curators;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.junit.Before;
import org.junit.Test;
import util.Threads;

import java.util.concurrent.TimeUnit;

/**
 * @author payno
 * @date 2019/12/26 15:25
 * @description
 */
public class InterProcessMutexLock {
    @Before
    public void init(){
        Curators.client().start();
    }

    @Test
    public void lock(){
        InterProcessMutex mutex=new InterProcessMutex(Curators.client(),"/lock/update");
        Runnable update=()->{
            try{
                System.out.println(Thread.currentThread().getName()+" try to acquire lock!");
                boolean holdLock=mutex.acquire(10, TimeUnit.SECONDS);
                if(holdLock){
                    System.out.println(Thread.currentThread().getName()+" hold lock!");
                    Threads.sleep(8000);
                }else{
                    System.out.println(Thread.currentThread().getName()+" try acquire 10 s but not acquire!");
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try{
                    mutex.release();
                    System.out.println(Thread.currentThread().getName()+" release lock!");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        new Thread(update,"a").start();
        new Thread(update,"b").start();
        new Thread(update,"c").start();
        Threads.sleep(30000);
    }
}

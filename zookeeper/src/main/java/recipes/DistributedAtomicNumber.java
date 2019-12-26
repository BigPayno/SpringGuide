package recipes;

import client.Curators;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.RetryNTimes;
import org.junit.Before;
import org.junit.Test;
import util.Threads;

import java.util.concurrent.Executors;

/**
 * @author payno
 * @date 2019/12/26 15:41
 * @description
 */
public class DistributedAtomicNumber {
    @Before
    public void init(){
        Curators.client().start();
    }

    @Test
    public void atomic() throws Exception{
        Curators.client().delete().forPath("/counter/onlineUser");
        DistributedAtomicInteger atomicInteger=new DistributedAtomicInteger(
                Curators.client(),"/counter/onlineUser", new RetryNTimes(3, 1000)
        );
        for(int i=0;i<100;i++){
            Curators.execute(()->{
                try{
                    boolean success=false;
                    while(!success){
                        success=atomicInteger.increment().succeeded();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
        Threads.sleep(30000);
        System.out.println( atomicInteger.get().postValue());
    }
}

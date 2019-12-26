package recipes;

import client.Curators;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author payno
 * @date 2019/12/26 16:01
 * @description
 */
public class SharedNumberWatcher {
    @Before
    public void init(){
        Curators.client().start();
    }

    @Test
    public void sharedNumber() throws Exception{
        SharedCount sharedCount=new SharedCount(Curators.client(),"/shared/count",0);
        AtomicBoolean needStop=new AtomicBoolean(false);
        sharedCount.addListener(new SharedCountListener() {
            @Override
            public void countHasChanged(SharedCountReader sharedCountReader, int i) throws Exception {
                System.out.println("Counter's value is changed to " + i);
                if(i>10){
                    needStop.set(true);
                }
            }

            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                System.out.println("State changed: " + connectionState.toString());
            }
        });
        sharedCount.start();
        while (!needStop.get()){
            Curators.execute(()->{
                try{
                    sharedCount.setCount(sharedCount.getCount()+1);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
        System.out.println(sharedCount.getCount());
    }
}

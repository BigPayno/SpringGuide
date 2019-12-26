package client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author payno
 * @date 2019/12/26 16:18
 * @description
 *      2009-2012 what are you doing?
 */
public class CuratorTransaction {
    @Before
    public void init(){
        Curators.client().start();
    }

    @After
    public void destroy(){
        Curators.client().close();
    }

    @Test
    public void transaction() throws Exception{
        Curators.client().inTransaction()
                .create().forPath("/transaction").and()
                .setData().forPath("/transaction","chad".getBytes()).and()
                .delete().forPath("/transaction").and().commit();
    }
}

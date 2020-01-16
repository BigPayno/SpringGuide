package com.payno.transaction;

import com.payno.transaction.demo.ITran;
import com.payno.transaction.demo.Obj;
import com.payno.transaction.demo.Tran5;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TransactionApplication.class)
class TransactionTests {

    @Autowired
    ITran tran;

    @Autowired
    @Qualifier("tran2")
    ITran tran2;

    @Autowired
    @Qualifier("tran3")
    ITran tran3;

    @Autowired
    @Qualifier("tran4")
    ITran tran4;

    @Autowired
    Tran5 tran5;

    /**
     * Spring事务支持任何的公共方法public
     * 如果是protected和private可能会失效
     */
    @Test
    void trans(){
        System.out.println(tran.getClass());
        System.out.println(tran2.getClass());
        System.out.println(tran3.getClass());
        System.out.println(tran4.getClass());
        System.out.println(tran5.getClass());
    }

    /**
     *  本质Spring的事务是对数据库事务的封装，换而言之
     *  事务方法，只保证数据库执行是事务的，而不是整个方法
     *  在该方法中就已经修改了
     */
    @Test
    void interfaceTran() throws Exception{
        Obj obj=new Obj();
        try{
            tran.init(obj);
        }catch (Exception e){
            System.out.println(obj.getVal());
        }
    }
}

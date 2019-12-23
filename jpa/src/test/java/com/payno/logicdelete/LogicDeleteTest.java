package com.payno.logicdelete;

import com.payno.jpa.JpaApplication;
import com.payno.jpa.logicdelete.annotation.LogicDelete;
import com.payno.jpa.logicdelete.annotation.LogicDeleteRepo;
import com.payno.jpa.logicdelete.superclass.Demo;
import com.payno.jpa.logicdelete.superclass.DemoRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author payno
 * @date 2019/12/23 08:54
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaApplication.class)
public class LogicDeleteTest {
    @Autowired
    LogicDeleteRepo repo;
    @Autowired
    DemoRepo repo2;
    @Test
    public void test(){
        LogicDelete delete=new LogicDelete();
        delete.setUserName("payno");
        repo.save(delete);
        repo.delete(delete);
    }

    @Test
    public void test2(){
        Demo demo=new Demo();
        demo.setDemo("hello,chad white");
        repo2.save(demo);
        repo2.logicDelete(demo);
    }
}

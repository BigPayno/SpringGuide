package com.payno.jpa;

import com.payno.jpa.audit.entity.Audit1;
import com.payno.jpa.audit.entity.Audit2;
import com.payno.jpa.audit.repo.Audit1Repo;
import com.payno.jpa.audit.repo.Audit2Repo;
import com.payno.jpa.demo.Data;
import com.payno.jpa.demo.DataRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpaApplication.class)
class JpaApplicationTests {

    @Autowired
    Audit1Repo audit1Repo;
    @Autowired
    Audit2Repo audit2Repo;
    @Autowired
    DataRepo dataRepo;

    /**
     * 插入时自带时间
     */
    @Test
    void audit1Save() throws Exception {
        Audit1 audit1 = Audit1.builder().description("nothing").title("no").build();
        audit1Repo.save(audit1);
        System.out.println(audit1);
    }

    /**
     * 更新时，数据库Version及时间发生变化
     * 应该是生成代理对象persist，所以代码中的对象时间及版本无变化
     */
    @Test
    void audit1Update() {
        Audit1 audit1 = audit1Repo.findById(Long.valueOf(9)).get();
        audit1.setTitle("new");
        System.out.println(audit1);
        audit1Repo.save(audit1);
        System.out.println(audit1);
    }

    @Test
    void audit2CreateBy() {
        Audit2 audit2 = Audit2.builder().title("test").build();
        audit2Repo.save(audit2);
        System.out.println(audit2);
    }

    /**
     * 看看忽略路径是否有用,以及抽象类的某些预定功能
     */
    @Test
    void data() {
        dataRepo.save(new Data());
        dataRepo.save(new Data());
        Data example = new Data();
        example.setType(2);
        dataRepo.findAll(example.toExample()).forEach(System.out::println);
    }
}

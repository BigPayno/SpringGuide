package com.payno.webmvc;

import com.payno.webmvc.web.dto.User;
import com.payno.webmvc.web.dto.Valid;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebmvcApplication.class)
class WebmvcApplicationTests {

    @Autowired
    Validator defaultValidator;

    @Test
    void contextLoads() {

    }

}

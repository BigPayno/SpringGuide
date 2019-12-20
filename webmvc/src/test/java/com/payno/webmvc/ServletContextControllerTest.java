package com.payno.webmvc;

import com.payno.webmvc.controller.ServletContextController;
import com.payno.webmvc.service.ServletContextService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author payno
 * @date 2019/12/20 11:44
 * @description
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ServletContextController.class)
public class ServletContextControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ServletContextService mock;
    @Test
    public void test(){
        /**
         * 忽略了，用到时候再说
         */
    }
}

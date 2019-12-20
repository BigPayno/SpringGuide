package com.payno.webmvc;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;
import com.google.common.io.ByteStreams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

/**
 * @author payno
 * @date 2019/12/20 10:41
 * @description
 *      集成测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebmvcApplication.class)
@AutoConfigureMockMvc
public class SecretControllerTest {
    @Autowired
    MockMvc mockMvc;
    private String exceptResult(String classpath){
        try{
            classpath=classpath.replace('/','-');
            classpath="mock/".concat(classpath);
            ClassPathResource resource=new ClassPathResource(classpath);
            byte[] bytes=ByteStreams.toByteArray(resource.getInputStream());
            return new String(bytes);
        }catch (IOException e){
            return null;
        }
    }
    @Test
    public void test() throws Exception{
        String url="/secret/1";
        /**
         * 模拟请求地址
         */
        ResultActions actions=mockMvc.perform(
                MockMvcRequestBuilders.get(url)
                    .contentType(MediaType.TEXT_PLAIN)
                    .accept(MediaType.APPLICATION_JSON)
        );
        actions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(exceptResult(url)));
    }
}

package com.payno.feign;

import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author payno
 * @date 2020/5/25 11:23
 * @description
 */
@SpringBootApplication
@RestController
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostMapping("/hello/{name}")
    public String test(@PathVariable("name") String name){
            return "hello,"+name;
    }

    @GetMapping("/hello")
    public String test2(HttpServletRequest request){
        return request.getParameter("name");
    }

    @PostMapping("/upload")
    public void test3(MultipartFile file) throws Exception{
        System.out.println(new String(
                ByteStreams.toByteArray(file.getInputStream()), Charsets.UTF_8
        ));
    }
}

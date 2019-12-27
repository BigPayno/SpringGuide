package com.payno.boot.config;

import com.payno.boot.api.Print;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2019/12/27 10:19
 * @description
 */
@Component
public class Runner implements ApplicationRunner {
    @Autowired
    Print print;

    public void run(ApplicationArguments args) throws Exception {
        System.out.println("auto configuration success!");
        print.print();
    }
}

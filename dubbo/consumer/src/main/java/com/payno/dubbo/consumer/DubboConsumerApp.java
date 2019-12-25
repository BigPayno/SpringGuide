package com.payno.dubbo.consumer;

import com.payno.dubbo.api.UserRpcService;
import com.payno.dubbo.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author payno
 * @date 2019/12/25 14:49
 * @description
 */
@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
public class DubboConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApp.class,args);
    }

    @Slf4j
    @Component
    public class UserRpcServiceTest implements CommandLineRunner {

        @Resource
        private UserRpcService userRpcService;

        public void run(String... args) throws Exception {
            UserDto user = userRpcService.get(1);
            log.info("[run][发起一次 Dubbo RPC 请求，获得用户为({})",user);
        }

    }
}

package com.payno.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2019/12/5 14:34
 * @description
 */
@Slf4j
@Component
public class RepoLogRunner implements ApplicationRunner, ApplicationContextAware {
    private ApplicationContext contextHolder;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        contextHolder=applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(String.format("%nJpa Repos:"));
        contextHolder.getBeansOfType(Repository.class).entrySet()
                .forEach(stringRepositoryEntry -> {
                    stringBuilder.append(
                            String.format(
                                    "%nJpa Repo Load:[Bean:%s][Type:%s]",
                                    stringRepositoryEntry.getKey(),
                                    stringRepositoryEntry.getValue().getClass()
                            ));
                });
        log.info(stringBuilder.toString());
    }
}

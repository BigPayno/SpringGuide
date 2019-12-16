package com.payno.jpa.config.multi;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author payno
 * @date 2019/12/5 11:32
 * @description
 */
@Profile("multi")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = MultiProperties.BASE_REPO_PACKAGE2,
        repositoryImplementationPostfix = MultiProperties.REPO2_POSTFIX,
        entityManagerFactoryRef = "entityManagerFactory2",
        transactionManagerRef = "transactionManager2"
)
public class JpaConfig2 {
    @Bean(name = "jpaProperties2")
    @ConfigurationProperties(MultiProperties.JPA_PREFIX2)
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

    @Bean("entityManagerFactory2")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dateSource2") DataSource primaryDataSource, @Qualifier("jpaProperties2") JpaProperties jpaProperties, EntityManagerFactoryBuilder builder) {
        return builder
                // 设置数据源
                .dataSource(primaryDataSource)
                // 设置jpa配置
                .properties(jpaProperties.getProperties())
                // 设置实体包名
                .packages(MultiProperties.BASE_PACKAGE2)
                // 设置持久化单元名，用于@PersistenceContext注解获取EntityManager时指定数据源
                .persistenceUnit("persistenceUnit2").build();
    }

    @Bean(name = "entityManager2")
    public EntityManager entityManager(@Qualifier("entityManagerFactory2") EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

    @Bean(name = "transactionManager2")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory2") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}

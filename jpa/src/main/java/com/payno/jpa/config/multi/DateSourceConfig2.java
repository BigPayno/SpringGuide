package com.payno.jpa.config.multi;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author payno
 * @date 2019/12/5 11:28
 * @description
 */
@Profile("multi")
@Configuration
public class DateSourceConfig2 {
    @Bean(name = "dataSourceProperties2")
    @ConfigurationProperties(MultiProperties.DATA_SOURCE_PREFIX2)
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "dateSource2")
    public DataSource dataSource(@Qualifier("dataSourceProperties2") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "jdbcTemplate2")
    public JdbcTemplate jdbcTemplate(@Qualifier("dateSource2") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}

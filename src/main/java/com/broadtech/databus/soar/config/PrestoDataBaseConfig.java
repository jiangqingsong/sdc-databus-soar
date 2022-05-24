package com.broadtech.databus.soar.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

/**
 * @program sdc-databus-soar
 * @description: presto 数据源配置
 * @author: 蒋青松
 * @create: 2022/05/23 18:32
 */
@Configuration
public class PrestoDataBaseConfig {

    @Value("${presto.datasource.url}")
    private String jdbcUrl;

    @Value("${presto.datasource.driverClassName}")
    private String driverName;

    @Value("${presto.datasource.userName}")
    private String userName;

    @Bean(name = "prestoJdbcDataBase")
    @Qualifier("prestoJdbcDataBase")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setDriverClassName(driverName);
        dataSource.setUsername(userName);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(1000 * 60 * 30);
        return dataSource;
    }

    @Bean(name = "prestoJdbcDataBaseTemplate")
    public JdbcTemplate prestoJdbcTemplate(@Qualifier("prestoJdbcDataBase") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}

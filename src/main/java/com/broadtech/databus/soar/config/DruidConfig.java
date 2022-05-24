package com.broadtech.databus.soar.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@RefreshScope
@EnableConfigurationProperties(DataSourceProperties.class)
public class DruidConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;



    @Bean(name="primaryDataSource")
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    @RefreshScope
    public DruidDataSource dataSource(DataSourceProperties properties){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(dbUrl);

        String validationQuery = databaseDriver.getValidationQuery();
        if(validationQuery != null){
            dataSource.setTestOnBorrow(true);
            dataSource.setValidationQuery(validationQuery);
        }
        return dataSource;
    }

    /**
     * 注册一个StatViewServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){

         ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
         //添加初始化参数
         servletRegistrationBean.setServlet(new StatViewServlet());
         servletRegistrationBean.setUrlMappings(Arrays.asList("/druid/*"));
         //白名单：
         servletRegistrationBean.addInitParameter("allow","");
         //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
         servletRegistrationBean.addInitParameter("deny","");
         //登录查看信息的账号密码.
         servletRegistrationBean.addInitParameter("loginUsername","admin");
         servletRegistrationBean.addInitParameter("loginPassword","admin");
         //是否能够重置数据.
         servletRegistrationBean.addInitParameter("resetEnable","true");

         return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter2(){

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

        return filterRegistrationBean;
    }

    @Bean
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}

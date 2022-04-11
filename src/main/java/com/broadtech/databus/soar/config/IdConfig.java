package com.broadtech.databus.soar.config;

import cn.hutool.core.lang.Snowflake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: leo.j
 * @desc: uuid工具
 * @Date: 2022/3/19 3:41 下午
 */
@Configuration
public class IdConfig {
    @Bean
    public Snowflake Snowflake() {
        Snowflake snowflake = new Snowflake(1, 1, true);
        return snowflake;
    }

}

package com.broadtech.databus.soar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
@MapperScan("com.broadtech.databus.soar.mapper")
public class SdcDatabusSoarApplication {

    public static void main(String[] args) {
        SpringApplication.run(SdcDatabusSoarApplication.class, args);
    }

}

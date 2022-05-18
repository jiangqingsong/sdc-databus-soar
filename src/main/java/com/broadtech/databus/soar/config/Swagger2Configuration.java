package com.broadtech.databus.soar.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@ConditionalOnProperty(prefix = "swagger", name = "enable", havingValue = "true", matchIfMissing = false)
public class Swagger2Configuration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.broadtech.databus.soar.controller"))//api的配置路径
                .paths(PathSelectors.any())//扫描路径选择
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SDC-DATABUS-SOAR REST API") //文档标题
                .description("SDC-DATABUS-SOAR模块接口文档")//接口概述
                .version("2.0") //版本号
                .termsOfServiceUrl(String.format("url"))//服务的域名
                //.license("LICENSE")//证书
                //.licenseUrl("http://www.guangxu.com")//证书的url
                .build();
    }
}
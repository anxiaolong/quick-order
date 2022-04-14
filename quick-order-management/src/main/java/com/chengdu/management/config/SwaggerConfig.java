package com.chengdu.management.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.rowset.Predicate;

/**
 * swagger配置类
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //是否开启 (true 开启  false隐藏。生产环境建议隐藏)
                //.enable(false)
                .select()
                //扫描的路径包,设置basePackage会将包下的所有被@Api标记类的所有方法作为api，配置多个路劲如下：
//                .apis(
//                        Predicates.or(
//                                RequestHandlerSelectors.basePackage("org.flowable.ui.task.rest")
//                                ,RequestHandlerSelectors.basePackage("org.flowable.ui.common.rest")
//                        )
//                )
                .apis(
                        Predicates.or(RequestHandlerSelectors.basePackage("com.chengdu.management.admin.controller"),
                                RequestHandlerSelectors.basePackage("com.chengdu.management.supplier.controller"))
                )
                //指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //设置文档标题(API名称)
                .title("quick-order-management接口")
                //文档描述
                .description("超级管理后台使用的接口")
                //服务条款URL
                //.termsOfServiceUrl("http://localhost:8080/")
                //版本号
                .version("1.0.0")
                .build();
    }
}

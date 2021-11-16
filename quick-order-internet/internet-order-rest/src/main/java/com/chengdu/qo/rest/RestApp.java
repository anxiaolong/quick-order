package com.chengdu.qo.rest;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfig
public class RestApp {
    public static void main(String[] args) {
        SpringApplication.run(RestApp.class,args);
    }
}

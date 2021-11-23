package com.chengdu.qo.rest.service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDubbo
@EnableScheduling
public class RestApp {
    public static void main(String[] args) {
        SpringApplication.run(RestApp.class,args);
    }
}

package com.chengdu.management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {
        "com.chengdu.management.admin.mapper",
        "com.chengdu.management.supplier.mapper"
})
public class ManagementApp {
    public static void main(String[] args) {
        SpringApplication.run(ManagementApp.class, args);
    }
}

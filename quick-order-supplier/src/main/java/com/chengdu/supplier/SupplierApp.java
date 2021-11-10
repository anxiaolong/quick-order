package com.chengdu.supplier;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {
        "com.chengdu.supplier.mapper"
})
public class SupplierApp {
    public static void main(String[] args) {
        SpringApplication.run(SupplierApp.class, args);
    }
}

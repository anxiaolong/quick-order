package com.chengdu.qo.rest.controller;

import com.chengdu.management.pojo.Customer;
import com.chengdu.qo.rest.costomer.service.CustomerService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @DubboReference
    private CustomerService customerService;

    @RequestMapping("/test")
    public Map<String,Object> test(){
        HashMap<String, Object> map = new HashMap<>();
        int i = customerService.customerRegister(new Customer());
        map.put("result",i);
        return map;
    }
}

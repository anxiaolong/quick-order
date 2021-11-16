package com.chengdu.qo.rest.costomer.service.impl;

import com.chengdu.management.pojo.Customer;
import com.chengdu.qo.rest.costomer.service.CustomerService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@Service
@DubboService
public class CustomerServiceImpl implements CustomerService {


    @Override
    public int customerRegister(Customer customer) {
        return 9999;
    }
}

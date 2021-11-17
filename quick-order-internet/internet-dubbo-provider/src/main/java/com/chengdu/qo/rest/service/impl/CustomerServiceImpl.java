package com.chengdu.qo.rest.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chengdu.management.pojo.Customer;
import com.chengdu.qo.rest.costomer.service.CustomerService;
import com.chengdu.qo.rest.mapper.CustomerMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Service
@Component
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;

    @Override
    public int customerRegister(Customer customer) {
        return customerMapper.insertCustomer(customer);
    }

}

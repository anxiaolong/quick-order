package com.chengdu.qo.rest.service.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chengdu.management.pojo.Customer;
import com.chengdu.qo.rest.service.CustomerService;
import com.chengdu.qo.rest.service.aop.SysLog;
import com.chengdu.qo.rest.service.mapper.CustomerMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Service
@Component
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;

    @SysLog
    @Override
    public int customerRegister(Customer customer) {
        return customerMapper.insertCustomer(customer);
    }

    @SysLog
    @Override
    public Customer selCustomerByUidAndPhone(String uid, String phone) {
        return customerMapper.selCustomerByUidAndPhone(uid,phone);
    }

}

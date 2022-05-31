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
    public Customer customerRegister(Customer customer) {
        Customer customer1 = customerMapper.selCountByPhone(customer.getPhone());
        if (customer1 != null) {
            return customer1;
        }

        if (customerMapper.insertCustomer(customer)==1){
            return customerMapper.selCountByPhone(customer.getPhone());
        }
        return null;
    }

    @SysLog
    @Override
    public Customer selCustomerByUidAndPhone(String uid, String phone) {
        return customerMapper.selCustomerByUidAndPhone(uid,phone);
    }

}

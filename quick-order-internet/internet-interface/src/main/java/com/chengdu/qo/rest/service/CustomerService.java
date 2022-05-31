package com.chengdu.qo.rest.service;

import com.chengdu.management.pojo.Customer;

public interface CustomerService {
    Customer customerRegister(Customer customer);

    Customer selCustomerByUidAndPhone(String uid,String phone);
}

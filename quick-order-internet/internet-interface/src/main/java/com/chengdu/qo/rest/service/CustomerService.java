package com.chengdu.qo.rest.service;

import com.chengdu.management.pojo.Customer;

public interface CustomerService {
    int customerRegister(Customer customer);

    Customer selCustomerByUidAndPhone(String uid,String phone);
}

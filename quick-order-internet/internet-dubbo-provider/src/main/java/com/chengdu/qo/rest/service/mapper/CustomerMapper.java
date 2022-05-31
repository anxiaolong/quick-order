package com.chengdu.qo.rest.service.mapper;

import com.chengdu.management.pojo.Customer;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    Integer insertCustomer(Customer customer);

    Customer selCustomerByUidAndPhone(
            @Param("uid") String uid, @Param("phone") String phone);

    Customer selCountByPhone(@Param("phone") String phone);
}

package com.chengdu.qo.rest.mapper;

import com.chengdu.management.pojo.Customer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CustomerMapper {
    @Select("select * from qo_internet_customer qic where" +
            " qic.uid = #{uid} and qic.phone = #{phone}")
    Customer selCustomerByUidAndPhone(
            @Param("uid") String uid,@Param("phone") String phone);
}

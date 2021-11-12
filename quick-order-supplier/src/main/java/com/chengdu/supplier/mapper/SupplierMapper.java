package com.chengdu.supplier.mapper;

import com.chengdu.management.pojo.SupplierInfo;
import org.apache.ibatis.annotations.Param;

public interface SupplierMapper {
    SupplierInfo selSupplierByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}

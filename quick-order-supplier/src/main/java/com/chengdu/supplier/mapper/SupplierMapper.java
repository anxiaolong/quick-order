package com.chengdu.supplier.mapper;

import org.apache.ibatis.annotations.Param;

public interface SupplierMapper {
    Integer selSupplierByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}

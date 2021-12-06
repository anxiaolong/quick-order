package com.chengdu.supplier.mapper;

import com.chengdu.management.pojo.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderInfoMapper {
    List<OrderInfo> selOrderInfoByDateAndSupplierId(@Param("supplierId") int supplierId,@Param("date") String date);
}

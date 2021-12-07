package com.chengdu.supplier.mapper;

import com.chengdu.management.pojo.OrderInfo;
import com.chengdu.management.pojo.OrderInfo_QO;

import java.util.List;

public interface OrderInfoMapper {

    List<OrderInfo> selOrderInfo(OrderInfo_QO orderInfo_qo);

    Integer countSelOrderInfo(OrderInfo_QO orderInfo_qo);

}

package com.chengdu.qo.rest.service.mapper;

import com.chengdu.management.pojo.OrderInfo;

import java.util.List;

public interface OrderInfoMapper {
    Integer insertOrderInfo(OrderInfo orderInfo);

    Integer updateGoodsCount(OrderInfo orderInfo);

    List<OrderInfo> selTimeOutOrder();

    Integer updateOrderStatus(OrderInfo orderInfo);

}

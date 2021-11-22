package com.chengdu.qo.rest.service.mapper;

import com.chengdu.management.pojo.OrderInfo;

public interface OrderInfoMapper {
    Integer insertOrderInfo(OrderInfo orderInfo);

    Integer updateGoodsCount(OrderInfo orderInfo);
}

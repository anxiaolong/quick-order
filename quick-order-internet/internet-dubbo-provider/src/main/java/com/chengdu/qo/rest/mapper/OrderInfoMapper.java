package com.chengdu.qo.rest.mapper;

import com.chengdu.management.pojo.OrderInfo;

public interface OrderInfoMapper {
    Integer insertOrderInfo(OrderInfo orderInfo);

    Integer updateGoodsCount(OrderInfo orderInfo);
}

package com.chengdu.qo.rest.service.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chengdu.management.pojo.OrderInfo;
import com.chengdu.qo.rest.service.mapper.OrderInfoMapper;
import com.chengdu.qo.rest.service.OrderService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Transactional
    @Override
    public int createOrder(OrderInfo orderInfo) {
        Integer updateStock = orderInfoMapper.updateGoodsCount(orderInfo);
        Integer createOrder = orderInfoMapper.insertOrderInfo(orderInfo);
        if (updateStock ==1 && createOrder == 1){
            return 1;
        }
        return 0;
    }
}

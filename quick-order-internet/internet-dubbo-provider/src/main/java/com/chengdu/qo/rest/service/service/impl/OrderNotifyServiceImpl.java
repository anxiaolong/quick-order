package com.chengdu.qo.rest.service.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chengdu.management.pojo.OrderInfo;
import com.chengdu.management.pojo.OrderNotify;
import com.chengdu.qo.rest.service.OrderNotifyService;
import com.chengdu.qo.rest.service.mapper.OrderInfoMapper;
import com.chengdu.qo.rest.service.mapper.OrderNotifyMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
@Service
public class OrderNotifyServiceImpl implements OrderNotifyService {
    @Resource
    private OrderNotifyMapper orderNotifyMapper;
    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public boolean saveOrderNotify(OrderNotify orderNotify) {
        if (orderNotifyMapper.insertOrderNotify(orderNotify) == 1){
            if (orderNotifyMapper.selPayAmountAll(orderNotify) >=
                    orderInfoMapper.selTotalPriceByOrderId(orderNotify.getOrder_id())){
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setStatus(2);
                orderInfo.setOrder_id(orderNotify.getOrder_id());
                orderInfoMapper.updateOrderStatus(orderInfo);
            }
            return true;
        }
        return false;
    }
}

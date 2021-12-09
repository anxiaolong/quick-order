package com.chengdu.qo.rest.service.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chengdu.management.pojo.OrderInfo;
import com.chengdu.management.pojo.OrderNotify;
import com.chengdu.qo.rest.service.OrderNotifyService;
import com.chengdu.qo.rest.service.aop.SysLog;
import com.chengdu.qo.rest.service.mapper.OrderInfoMapper;
import com.chengdu.qo.rest.service.mapper.OrderNotifyMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
@Component
@Service
public class OrderNotifyServiceImpl implements OrderNotifyService {
    @Resource
    private OrderNotifyMapper orderNotifyMapper;
    @Resource
    private OrderInfoMapper orderInfoMapper;

    @SysLog
    @Transactional
    @Override
    public boolean saveOrderNotify(OrderNotify orderNotify) {
        if (orderNotifyMapper.insertOrderNotify(orderNotify) == 1){
            Integer totalPrice = orderInfoMapper.selTotalPriceByOrderId(orderNotify.getOrder_id());

            // 支付成功的通知逻辑处理
            if (orderNotify.getPay_status() == 0){
                if (orderNotifyMapper.selPayAmountAll(orderNotify) >= totalPrice){
                    OrderInfo orderInfo = new OrderInfo();
                    orderInfo.setStatus(2);
                    orderInfo.setOrder_id(orderNotify.getOrder_id());
                    orderInfoMapper.updateOrderStatus(orderInfo);
                }
            }
            // 退款通知逻辑处理
            if (orderNotify.getPay_status() == 1){
                if (orderNotifyMapper.selPayAmountAll(orderNotify) <= 0){
                    OrderInfo orderInfo = new OrderInfo();
                    orderInfo.setStatus(5);
                    orderInfo.setOrder_id(orderNotify.getOrder_id());
                    orderInfoMapper.updateOrderStatus(orderInfo);
                }
            }

            return true;
        }
        return false;
    }
}

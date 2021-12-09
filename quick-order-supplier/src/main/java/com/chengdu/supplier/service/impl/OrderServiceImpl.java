package com.chengdu.supplier.service.impl;

import com.chengdu.management.pojo.OrderInfo_QO;
import com.chengdu.supplier.aop.SysLog;
import com.chengdu.supplier.mapper.OrderInfoMapper;
import com.chengdu.supplier.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderInfoMapper orderInfoMapper;


    @SysLog
    @Override
    public OrderInfo_QO getOrderInfoList(OrderInfo_QO orderInfo_qo) {

        orderInfo_qo.setPageIndex((orderInfo_qo.getPageIndex()-1)*orderInfo_qo.getPageSize());
        orderInfo_qo.setOrderInfos(orderInfoMapper.selOrderInfo(orderInfo_qo));
        orderInfo_qo.setCount(orderInfoMapper.countSelOrderInfo(orderInfo_qo));

        return orderInfo_qo;
    }
}

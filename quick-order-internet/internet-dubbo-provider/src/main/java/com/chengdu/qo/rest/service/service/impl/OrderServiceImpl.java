package com.chengdu.qo.rest.service.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chengdu.management.pojo.OrderInfo;
import com.chengdu.qo.rest.service.mapper.GoodsStockMapper;
import com.chengdu.qo.rest.service.mapper.OrderInfoMapper;
import com.chengdu.qo.rest.service.OrderService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private GoodsStockMapper goodsStockMapper;

    @Transactional
    @Override
    public int createOrder(OrderInfo orderInfo) {
        Integer updateStock = goodsStockMapper.updateGoodsCount(orderInfo);
        Integer createOrder = orderInfoMapper.insertOrderInfo(orderInfo);
        if (updateStock ==1 && createOrder == 1){
            return 1;
        }
        return 0;
    }

    @Transactional
    @Override
    public void orderTimeoutService() {
        List<OrderInfo> orderInfos = orderInfoMapper.selTimeOutOrder();
        for (OrderInfo orderInfo : orderInfos) {
            orderInfo.setStatus(1);
            orderInfo.setGoods_count(-orderInfo.getGoods_count());

            orderInfoMapper.updateOrderStatus(orderInfo);
            goodsStockMapper.updateGoodsCount(orderInfo);
        }
    }

}

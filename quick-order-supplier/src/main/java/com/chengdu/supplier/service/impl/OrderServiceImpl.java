package com.chengdu.supplier.service.impl;

import com.chengdu.management.pojo.OrderInfo;
import com.chengdu.supplier.mapper.OrderInfoMapper;
import com.chengdu.supplier.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public List<OrderInfo> getOrderInfoList(int supplierId, String date) {
        return orderInfoMapper.selOrderInfoByDateAndSupplierId(supplierId,date);
    }
}

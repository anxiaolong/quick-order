package com.chengdu.supplier.service;

import com.chengdu.management.pojo.OrderInfo;

import java.util.List;

public interface OrderService {
    List<OrderInfo> getOrderInfoList(int supplierId,String date);
}

package com.chengdu.qo.rest.service;

import com.chengdu.management.pojo.OrderInfo;

public interface OrderService {

    int createOrder(OrderInfo orderInfo);

    void orderTimeoutService();

    boolean cancelOrder(String orderId);
}

package com.chengdu.qo.rest.service;

import com.chengdu.management.pojo.OrderInfo;

import java.util.List;
import java.util.Map;

public interface OrderService {

    int createOrder(OrderInfo orderInfo);

    void orderTimeoutService();

    boolean cancelOrder(String orderId);

    boolean orderRefund(String orderId);

    List<Map<String,Object>> selOrderInfoByPhone(String phone);
}

package com.chengdu.qo.rest.service.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.management.pojo.Customer;
import com.chengdu.management.pojo.OrderInfo;
import com.chengdu.qo.rest.service.CustomerService;
import com.chengdu.qo.rest.service.GoodsStockService;
import com.chengdu.qo.rest.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/order",produces = "application/json")
public class OrderController {
    @Reference
    private OrderService orderService;
    @Reference
    private GoodsStockService goodsStockService;
    @Reference
    private CustomerService customerService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResponse syncUser(@RequestBody JSONObject requestJson){
        String uid = requestJson.getJSONObject("data").getString("uid");
        String phone = requestJson.getJSONObject("data").getString("phone");
        String saleDate = requestJson.getJSONObject("data").getString("saleDate");
        Integer goods_id = requestJson.getJSONObject("data").getInteger("goods_id");
        Integer goods_count = requestJson.getJSONObject("data").getInteger("goods_count");

        Integer stock = goodsStockService.selCountByGoodsIdAndSaleDate(goods_id, saleDate);
        Customer customer = customerService.selCustomerByUidAndPhone(uid, phone);

        boolean flag =
                customer != null &&
                stock != 0 &&
                stock >= goods_count;
        if (flag) {
            OrderInfo orderInfo = requestJson.getObject("data", OrderInfo.class);
            String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            orderInfo.setCreate_time(dateTime);
            orderInfo.setModified_time(dateTime);
            if (orderService.createOrder(orderInfo) == 1){
                return new CommonResponse(CommonResponseEnum.Success,null);
            }
        }

        return new CommonResponse(CommonResponseEnum.Fail,null);
    }
}

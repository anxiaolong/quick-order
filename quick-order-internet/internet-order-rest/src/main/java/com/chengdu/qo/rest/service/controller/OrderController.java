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
import com.chengdu.qo.rest.service.aop.SysLog;
import org.apache.commons.lang3.StringUtils;
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

    @SysLog
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public synchronized CommonResponse createOrder(@RequestBody JSONObject requestJson){
        String uid = requestJson.getJSONObject("data").getString("uid");
        String phone = requestJson.getJSONObject("data").getString("phone");
        String saleDate = requestJson.getJSONObject("data").getString("saleDate");
        Integer goods_id = requestJson.getJSONObject("data").getInteger("goods_id");
        Integer goods_count = requestJson.getJSONObject("data").getInteger("goods_count");

        Integer stock = goodsStockService.selCountByGoodsIdAndSaleDate(goods_id, saleDate);
        Customer customer = customerService.selCustomerByUidAndPhone(uid, phone);

        if (customer == null){
            return new CommonResponse(CommonResponseEnum.Fail,"未授权手机号，不能下单");
        }
        if (stock == 0){
            return new CommonResponse(CommonResponseEnum.Fail,"已售罄");
        }
        if (stock < goods_count){
            return new CommonResponse(CommonResponseEnum.Fail,"库存不足");
        }

        OrderInfo orderInfo = requestJson.getObject("data", OrderInfo.class);
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        orderInfo.setCreate_time(dateTime);
        orderInfo.setModified_time(dateTime);
        if (orderService.createOrder(orderInfo) == 1){
            return new CommonResponse(CommonResponseEnum.Success,"下单成功");
        }

        return new CommonResponse(CommonResponseEnum.Fail,"下单失败");
    }

    @SysLog
    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    public CommonResponse cancelOrder(@RequestBody JSONObject requestJson){
        String orderId = requestJson.getJSONObject("data").getString("order_id");
        if (StringUtils.isNotBlank(orderId)){
            if (orderService.cancelOrder(orderId)){
                return new CommonResponse(CommonResponseEnum.Success,"取消订单成功！");
            }
        }
        return new CommonResponse(CommonResponseEnum.Fail,"取消订单失败！");
    }

    @SysLog
    @RequestMapping(value = "/refund",method = RequestMethod.POST)
    public CommonResponse orderRefund(@RequestBody JSONObject requestJson){
        String orderId = requestJson.getJSONObject("data").getString("order_id");
        if (StringUtils.isNotBlank(orderId)){
            if (orderService.orderRefund(orderId)){
                return new CommonResponse(CommonResponseEnum.Success,"退款已发起，预计3个工作日到账");
            }
        }
        return new CommonResponse(CommonResponseEnum.Fail,"订单退款失败！");
    }

}

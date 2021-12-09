package com.chengdu.supplier.controller;

import com.alibaba.fastjson.JSONObject;
import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.management.pojo.OrderInfo_QO;
import com.chengdu.supplier.aop.SysLog;
import com.chengdu.supplier.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/order",produces = "application/json")
public class OrderController {
    @Resource
    private OrderService orderService;


    @SysLog
    @RequestMapping(value = "/query/{supplierId}",method = RequestMethod.POST)
    public CommonResponse addGoods(@RequestBody OrderInfo_QO orderInfo_qo, @PathVariable("supplierId") int supplierId){
        orderInfo_qo.setSupplierId(supplierId);
        JSONObject jsonObject = new JSONObject();

        OrderInfo_QO orderInfoList = orderService.getOrderInfoList(orderInfo_qo);
        jsonObject.put("count",orderInfoList.getCount());
        jsonObject.put("orderInfos",orderInfoList.getOrderInfos());
        return new CommonResponse(CommonResponseEnum.Success,jsonObject);
    }

}

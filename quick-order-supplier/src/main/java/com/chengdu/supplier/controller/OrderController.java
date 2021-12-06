package com.chengdu.supplier.controller;

import com.alibaba.fastjson.JSONObject;
import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.supplier.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/order",produces = "application/json")
public class OrderController {
    @Resource
    private OrderService orderService;


    @RequestMapping(value = "/query/{supplierId}",method = RequestMethod.POST)
    public CommonResponse addGoods(@RequestBody JSONObject jsonObject, @PathVariable("supplierId") int supplierId){
        String date = jsonObject.getString("date");
        return new CommonResponse(CommonResponseEnum.Success,orderService.getOrderInfoList(supplierId,date));
    }

}

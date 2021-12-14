package com.chengdu.supplier.controller;

import com.alibaba.fastjson.JSONObject;
import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.management.pojo.OrderInfo_QO;
import com.chengdu.supplier.aop.SysLog;
import com.chengdu.supplier.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@Api(tags = "订单管理接口")
@RestController
@RequestMapping(value = "/order",produces = "application/json")
public class OrderController {
    @Resource
    private OrderService orderService;

    @ApiOperation(value = "分页查询订单信息",notes = "查询指定供应商的订单信息")
    @SysLog
    @RequestMapping(value = "/query/{supplierId}",method = RequestMethod.POST)
    public CommonResponse queryOrderInfo(@RequestBody OrderInfo_QO orderInfo_qo, @PathVariable("supplierId") int supplierId){
        orderInfo_qo.setSupplierId(supplierId);
        JSONObject jsonObject = new JSONObject();

        OrderInfo_QO orderInfoList = orderService.getOrderInfoList(orderInfo_qo);
        jsonObject.put("count",orderInfoList.getCount());
        jsonObject.put("orderInfos",orderInfoList.getOrderInfos());
        return new CommonResponse(CommonResponseEnum.Success,jsonObject);
    }

}

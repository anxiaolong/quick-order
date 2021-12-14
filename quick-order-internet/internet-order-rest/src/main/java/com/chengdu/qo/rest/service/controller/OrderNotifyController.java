package com.chengdu.qo.rest.service.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.management.pojo.OrderNotify;
import com.chengdu.qo.rest.service.OrderNotifyService;
import com.chengdu.qo.rest.service.aop.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "订单通知相关接口")
@RestController
@RequestMapping(value = "/order",produces = "application/json")
public class OrderNotifyController {
    @Reference
    private OrderNotifyService orderNotifyService;

    @ApiOperation(value = "三方支付通知接口",notes = "针对订单的支付相关的通知，包括支付退款等")
    @SysLog
    @RequestMapping(value = "/notify",method = RequestMethod.POST)
    public CommonResponse orderNotify(@RequestBody OrderNotify orderNotify){
        if (orderNotifyService.saveOrderNotify(orderNotify)){
            return new CommonResponse(CommonResponseEnum.Success,null);
        }
        return new CommonResponse(CommonResponseEnum.Fail,null);
    }
}

package com.chengdu.qo.rest.service.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.management.pojo.OrderNotify;
import com.chengdu.qo.rest.service.OrderNotifyService;
import com.chengdu.qo.rest.service.aop.SysLog;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order",produces = "application/json")
public class OrderNotifyController {
    @Reference
    private OrderNotifyService orderNotifyService;

    @SysLog
    @RequestMapping(value = "/notify",method = RequestMethod.POST)
    public CommonResponse syncUser(@RequestBody OrderNotify orderNotify){
        if (orderNotifyService.saveOrderNotify(orderNotify)){
            return new CommonResponse(CommonResponseEnum.Success,null);
        }
        return new CommonResponse(CommonResponseEnum.Fail,null);
    }
}

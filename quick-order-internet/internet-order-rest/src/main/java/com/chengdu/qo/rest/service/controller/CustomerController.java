package com.chengdu.qo.rest.service.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.management.pojo.Customer;
import com.chengdu.qo.rest.service.CustomerService;
import com.chengdu.qo.rest.service.aop.SysLog;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "用户相关接口")
@RestController
@RequestMapping(value = "/customer",produces = "application/json")
public class CustomerController {
    @Reference
    private CustomerService customerService;

    @ApiOperation(value = "用户同步接口",notes = "其他平台同步用户id和手机手机号到本系统")
    @SysLog
    @RequestMapping(value = "/sync/user",method = RequestMethod.POST)
    public CommonResponse syncUser(@RequestBody JSONObject requestJson){
        Customer customer = customerService.customerRegister(requestJson.getObject("data", Customer.class));
        if (customer != null){
            return new CommonResponse(CommonResponseEnum.Success,customer);
        }
        return new CommonResponse(CommonResponseEnum.Fail,null);
    }
}

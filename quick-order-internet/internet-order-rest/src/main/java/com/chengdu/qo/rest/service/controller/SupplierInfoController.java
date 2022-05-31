package com.chengdu.qo.rest.service.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.management.pojo.Customer;
import com.chengdu.qo.rest.service.CustomerService;
import com.chengdu.qo.rest.service.SupplierInfoService;
import com.chengdu.qo.rest.service.aop.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "商家相关接口")
@RestController
@RequestMapping(value = "/supplier",produces = "application/json")
public class SupplierInfoController {
    @Reference
    private SupplierInfoService supplierInfoService;
    @Reference
    private CustomerService customerService;

    @ApiOperation(value = "获取商家列表",notes = "获取商家列表")
    @SysLog
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public CommonResponse syncUser(@RequestBody JSONObject jsonObject){
        Customer customer = customerService.selCustomerByUidAndPhone(jsonObject.getJSONObject("data").getString("uid"),
                jsonObject.getJSONObject("data").getString("phone"));
        if (customer != null) {
            return new CommonResponse(CommonResponseEnum.Success,supplierInfoService.selSupplierInfoList());
        }
        return new CommonResponse(CommonResponseEnum.Fail,null);
    }
}

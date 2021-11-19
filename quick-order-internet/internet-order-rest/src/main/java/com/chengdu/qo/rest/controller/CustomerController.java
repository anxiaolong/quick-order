package com.chengdu.qo.rest.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.common.util.CommonSignUtils;
import com.chengdu.management.pojo.Customer;
import com.chengdu.qo.rest.costomer.service.CustomerService;
import com.chengdu.qo.rest.mapper.PartnerKeyMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/customer",produces = "application/json")
public class CustomerController {
    @Reference
    private CustomerService customerService;
    @Resource
    private PartnerKeyMapper partnerKeyMapper;

    @RequestMapping(value = "/sync/user",method = RequestMethod.POST)
    public CommonResponse syncUser(@RequestBody JSONObject requestJson){
        Integer partner = requestJson.getInteger("partner");
        if (partner != null) {
            String key = partnerKeyMapper.selKeyByPartnerId(partner);
            String sign = CommonSignUtils.getSign(requestJson.getJSONObject("data").toJSONString(), key);
            if (sign.equals(requestJson.getString("sign"))){
                if (customerService.customerRegister(requestJson.getObject("data", Customer.class)) == 1){
                    return new CommonResponse(CommonResponseEnum.Success,null);
                }
            }
        }

        return new CommonResponse(CommonResponseEnum.Fail,null);
    }
}

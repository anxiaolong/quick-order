package com.chengdu.qo.rest.service.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.qo.rest.service.GoodsStockService;
import com.chengdu.qo.rest.service.PartnerKeyService;
import com.chengdu.qo.rest.service.aop.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "商品库存相关接口")
@RestController
@RequestMapping(value = "/stock",produces = "application/json")
public class GoodsStockController {
    @Reference
    private GoodsStockService goodsStockService;
    @Reference
    private PartnerKeyService partnerKeyService;

    @ApiOperation(value = "当日可售商品库存",notes = "查询当日可售商品库存")
    @SysLog
    @RequestMapping(value = "/query/today",method = RequestMethod.POST)
    public CommonResponse queryStockToday(@RequestBody JSONObject requestJson){
        String date = requestJson.getJSONObject("data").getString("date");
        int supplierId = requestJson.getJSONObject("data").getInteger("supplierId");

        if (StringUtils.isNotBlank(date)){
            return new CommonResponse(CommonResponseEnum.Success,goodsStockService.queryStockByDate(date,supplierId));
        }

        return new CommonResponse(CommonResponseEnum.Fail,null);
    }
}

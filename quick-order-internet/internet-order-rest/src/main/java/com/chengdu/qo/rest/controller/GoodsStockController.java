package com.chengdu.qo.rest.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.common.util.CommonSignUtils;
import com.chengdu.qo.rest.mapper.PartnerKeyMapper;
import com.chengdu.qo.rest.stock.service.GoodsStockService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/stock",produces = "application/json")
public class GoodsStockController {
    @Reference
    private GoodsStockService goodsStockService;
    @Resource
    private PartnerKeyMapper partnerKeyMapper;

    @RequestMapping(value = "/query/today",method = RequestMethod.POST)
    public CommonResponse queryStockToday(@RequestBody JSONObject requestJson){
        Integer partner = requestJson.getInteger("partner");
        if (partner != null) {
            String key = partnerKeyMapper.selKeyByPartnerId(partner);
            String sign = CommonSignUtils.getSign(requestJson.getJSONObject("data").toJSONString(), key);
            if (sign.equals(requestJson.getString("sign"))){
                String date = requestJson.getJSONObject("data").getString("date");
                int supplierId = requestJson.getJSONObject("data").getInteger("supplierId");
                if (StringUtils.isNotBlank(date)){
                    return new CommonResponse(CommonResponseEnum.Success,goodsStockService.queryStockByDate(date,supplierId));
                }
            }
        }

        return new CommonResponse(CommonResponseEnum.Fail,null);
    }
}

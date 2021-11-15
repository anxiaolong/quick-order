package com.chengdu.supplier.controller;

import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.management.pojo.GoodsStock;
import com.chengdu.supplier.service.GoodsStockService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/goods/stock/",produces = "application/json")
public class GoodsStockController {
    @Resource
    private GoodsStockService goodsStockService;

    @RequestMapping(value = "/update/{goodsId}/{id}",method = RequestMethod.PUT)
    public CommonResponse updateGoodsStock(@RequestBody GoodsStock goodsStock,
                                           @PathVariable("goodsId") int goodsId,@PathVariable("id") int id){
        goodsStock.setGoods_id(goodsId);
        goodsStock.setId(id);
        if (goodsStockService.updateGoodsStock(goodsStock) == 1){
            return new CommonResponse(CommonResponseEnum.Success,null);
        }
        return new CommonResponse(CommonResponseEnum.Fail,null);
    }

    @RequestMapping(value = "/add/{goodsId}",method = RequestMethod.POST)
    public CommonResponse addGoodsStock(@RequestBody GoodsStock goodsStock,
                                           @PathVariable("goodsId") int goodsId){
        if (goodsId != 0){
            goodsStock.setGoods_id(goodsId);
            if (goodsStockService.addGoodsStock(goodsStock) == 1){
                return new CommonResponse(CommonResponseEnum.Success,null);
            }
        }
        return new CommonResponse(CommonResponseEnum.Fail,null);
    }

}

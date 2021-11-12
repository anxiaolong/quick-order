package com.chengdu.supplier.controller;

import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.management.pojo.Goods;
import com.chengdu.supplier.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/supplier",produces = "application/json")
public class GoodsController {
    @Resource
    private GoodsService goodsService;


    @RequestMapping(value = "/goods/add/{supplierId}",method = RequestMethod.POST)
    public CommonResponse addGoods(@RequestBody Goods goods, @PathVariable("supplierId") int supplierId){
        if (goods != null && supplierId != 0) {
            goods.setGoods_supplier_id(supplierId);
            if (goodsService.addGoodsService(goods,supplierId) == 1){
                return new CommonResponse(CommonResponseEnum.Success,null);
            }
        }
        return new CommonResponse(CommonResponseEnum.Fail,null);
    }

    @RequestMapping(value = "/goods/update/{supplierId}/{goodsId}",method = RequestMethod.POST)
    public CommonResponse addUpdate(@RequestBody Goods goods,
                                    @PathVariable("supplierId") int supplierId,@PathVariable("goodsId") int goodsId){
        if (goods != null && supplierId != 0 && goodsId != 0) {
            goods.setGoods_supplier_id(supplierId);
            goods.setGoods_id(goodsId);
            if (goodsService.updateGoodsService(goods) == 1){
                return new CommonResponse(CommonResponseEnum.Success,null);
            }
        }
        return new CommonResponse(CommonResponseEnum.Fail,null);
    }

}

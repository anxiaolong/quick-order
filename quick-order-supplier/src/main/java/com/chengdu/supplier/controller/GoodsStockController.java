package com.chengdu.supplier.controller;

import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.management.pojo.GoodsStock;
import com.chengdu.management.pojo.GoodsStock_QO;
import com.chengdu.supplier.aop.SysLog;
import com.chengdu.supplier.service.GoodsStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@Api(tags = "商品库存管理接口")
@RestController
@RequestMapping(value = "/goods/stock/",produces = "application/json")
public class GoodsStockController {
    @Resource
    private GoodsStockService goodsStockService;

    @ApiOperation(value = "修改商品库存",notes = "修改商品库存信息")
    @SysLog
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

    @ApiOperation(value = "添加商品库存",notes = "添加商品库存信息")
    @SysLog
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

    @ApiOperation(value = "查询商品库存列表",notes = "查询商品库存列表")
    @SysLog
    @RequestMapping(value = "/list/{supplier_id}",method = RequestMethod.POST)
    public CommonResponse getGoodsStockListPage(@RequestBody GoodsStock_QO goodsStock_qo,
                                                @PathVariable("supplier_id") int supplier_id ){

        goodsStock_qo.setSupplier_id(supplier_id);
        goodsStock_qo.setPageIndex((goodsStock_qo.getPageIndex()-1) * goodsStock_qo.getPageSize());
        GoodsStock_QO goodsStockListPage = goodsStockService.getGoodsStockListPage(goodsStock_qo);
        HashMap<Object, Object> dataMap = new HashMap<>();
        dataMap.put("goodsStockList",goodsStock_qo.getGoodsStockList());
        dataMap.put("count",goodsStock_qo.getCount());

        return new CommonResponse(CommonResponseEnum.Success,dataMap);
    }

}

package com.chengdu.supplier.controller;

import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.management.pojo.Goods;
import com.chengdu.management.pojo.Goods_QO;
import com.chengdu.supplier.aop.SysLog;
import com.chengdu.supplier.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@Api(tags = "商品管理接口")
@RestController
@RequestMapping(value = "/supplier",produces = "application/json")
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    @ApiOperation(value = "供应商添加商品",notes = "供应商添加自己的商品")
    @SysLog
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

    @ApiOperation(value = "供应商修改商品信息",notes = "修改商品信息")
    @SysLog
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

    @ApiOperation(value = "供应商分页查询商品信息",notes = "分页查询商品信息")
    @SysLog
    @RequestMapping(value = "/goods/list/{supplierId}",method = RequestMethod.POST)
    public CommonResponse listGoods(@RequestBody Goods_QO goods_qo, @PathVariable("supplierId") int supplierId){
        goods_qo.setGoods_supplier_id(supplierId);
        goods_qo.setPageIndex((goods_qo.getPageIndex()-1)*goods_qo.getPageSize());
        Goods_QO goods_qo1 = goodsService.listGoods(goods_qo);
        Map<String,Object> map = new HashMap<>();
        map.put("goodsInfos",goods_qo1.getGoodsInfos());
        map.put("count",goods_qo1.getCount());
        return new CommonResponse(CommonResponseEnum.Success,map);
    }


}

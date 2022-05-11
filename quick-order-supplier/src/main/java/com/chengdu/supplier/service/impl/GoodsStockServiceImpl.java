package com.chengdu.supplier.service.impl;

import com.chengdu.management.pojo.GoodsStock;
import com.chengdu.management.pojo.GoodsStock_QO;
import com.chengdu.supplier.aop.SysLog;
import com.chengdu.supplier.mapper.GoodsMapper;
import com.chengdu.supplier.mapper.GoodsStockMapper;
import com.chengdu.supplier.service.GoodsStockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GoodsStockServiceImpl implements GoodsStockService {
    @Resource
    private GoodsStockMapper goodsStockMapper;
    @Resource
    private GoodsMapper goodsMapper;

    @SysLog
    @Override
    public int updateGoodsStock(GoodsStock goodsStock) {
        return goodsStockMapper.updateGoodsStock(goodsStock);
    }

    @SysLog
    @Override
    public int addGoodsStock(GoodsStock goodsStock) {
        if (goodsMapper.selGoodsById(goodsStock.getGoods_id()) != null){
            return goodsStockMapper.insertGoodsStock(goodsStock);
        }
        return 0;
    }

    @Override
    public GoodsStock_QO getGoodsStockListPage(GoodsStock_QO goodsStock_qo) {
        goodsStock_qo.setGoodsStockList(goodsStockMapper.getGoodsStockListPage(goodsStock_qo));
        goodsStock_qo.setCount(goodsStockMapper.countGoodsStock(goodsStock_qo));
        return goodsStock_qo;
    }
}

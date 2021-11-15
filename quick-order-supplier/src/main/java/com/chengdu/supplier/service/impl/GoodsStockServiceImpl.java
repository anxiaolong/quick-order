package com.chengdu.supplier.service.impl;

import com.chengdu.management.pojo.GoodsStock;
import com.chengdu.supplier.mapper.GoodsStockMapper;
import com.chengdu.supplier.service.GoodsStockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GoodsStockServiceImpl implements GoodsStockService {
    @Resource
    private GoodsStockMapper goodsStockMapper;

    @Override
    public int updateGoodsStock(GoodsStock goodsStock) {
        return goodsStockMapper.updateGoodsStock(goodsStock);
    }

    @Override
    public int addGoodsStock(GoodsStock goodsStock) {
        return goodsStockMapper.insertGoodsStock(goodsStock);
    }
}

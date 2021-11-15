package com.chengdu.supplier.service;

import com.chengdu.management.pojo.GoodsStock;

public interface GoodsStockService {
    int updateGoodsStock(GoodsStock goodsStock);

    int addGoodsStock(GoodsStock goodsStock);
}

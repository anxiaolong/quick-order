package com.chengdu.supplier.service;

import com.chengdu.management.pojo.GoodsStock;
import com.chengdu.management.pojo.GoodsStock_QO;

public interface GoodsStockService {
    int updateGoodsStock(GoodsStock goodsStock);

    int addGoodsStock(GoodsStock goodsStock);

    GoodsStock_QO getGoodsStockListPage(GoodsStock_QO goodsStock_qo);
}

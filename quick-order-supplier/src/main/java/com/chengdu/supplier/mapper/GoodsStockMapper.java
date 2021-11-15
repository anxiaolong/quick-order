package com.chengdu.supplier.mapper;

import com.chengdu.management.pojo.GoodsStock;

public interface GoodsStockMapper {

    Integer updateGoodsStock(GoodsStock goodsStock);

    Integer insertGoodsStock(GoodsStock goodsStock);
}

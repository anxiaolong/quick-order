package com.chengdu.supplier.service;

import com.chengdu.management.pojo.Goods;

public interface GoodsService {
    int addGoodsService(Goods goods,int supplierId);

    int updateGoodsService(Goods goods);
}

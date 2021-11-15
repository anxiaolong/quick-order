package com.chengdu.supplier.service;

import com.chengdu.management.pojo.Goods;
import com.chengdu.management.pojo.Goods_QO;

public interface GoodsService {
    int addGoodsService(Goods goods,int supplierId);

    int updateGoodsService(Goods goods);

    Goods_QO listGoods(Goods_QO goods_qo);
}

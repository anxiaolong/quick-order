package com.chengdu.supplier.service;

import com.chengdu.management.pojo.Goods;
import com.chengdu.management.pojo.Goods_QO;
import org.apache.ibatis.annotations.Param;

public interface GoodsService {
    int addGoodsService(Goods goods,int supplierId);

    int updateGoodsService(Goods goods);

    Goods_QO listGoods(Goods_QO goods_qo);

    int updateGoodsStatus(int goods_id,int goods_status);

    Goods selGoodsById(int goodsId);
}

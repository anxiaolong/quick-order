package com.chengdu.supplier.mapper;

import com.chengdu.management.pojo.Goods;
import com.chengdu.management.pojo.Goods_QO;
import com.chengdu.management.pojo.SupplierInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    SupplierInfo selSupplierById(@Param("supplierId") int supplierId);

    int insertGoods(Goods goods);

    int updateGoods(Goods goods);

    List<Goods> listGoods(Goods_QO goods_qo);

    Integer countGoods(Goods_QO goods_qo);

    Goods selGoodsById(@Param("goodsId") int goodsId);
}

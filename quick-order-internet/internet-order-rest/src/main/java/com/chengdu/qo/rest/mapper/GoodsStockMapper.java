package com.chengdu.qo.rest.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface GoodsStockMapper {
    @Select("select qsgs.goods_count from qo_supplier_goods_stock qsgs where" +
            " qsgs.goods_id = #{goodsId} and qsgs.goods_sale_date = #{saleDate}")
    Integer selCountByGoodsIdAndSaleDate(
            @Param("goodsId") int goodsId,@Param("saleDate") String saleDate);
}

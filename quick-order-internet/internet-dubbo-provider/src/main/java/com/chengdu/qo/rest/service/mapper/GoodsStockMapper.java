package com.chengdu.qo.rest.service.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface GoodsStockMapper {
    List<HashMap<String,String>> selStockByDate(@Param("date") String date,
                                               @Param("supplierId") int supplierId);

    @Select("select qsgs.goods_count from qo_supplier_goods_stock qsgs where" +
            " qsgs.goods_id = #{goodsId} and qsgs.goods_sale_date = #{saleDate}")
    Integer selCountByGoodsIdAndSaleDate(
            @Param("goodsId") int goodsId,@Param("saleDate") String saleDate);
}

package com.chengdu.supplier.mapper;

import com.chengdu.management.pojo.GoodsStock;
import com.chengdu.management.pojo.GoodsStock_QO;
import com.chengdu.management.pojo.GoodsStock_VO;

import java.util.List;

public interface GoodsStockMapper {

    Integer updateGoodsStock(GoodsStock goodsStock);

    Integer insertGoodsStock(GoodsStock goodsStock);

    List<GoodsStock_VO> getGoodsStockListPage(GoodsStock_QO goodsStock_qo);

    Integer countGoodsStock(GoodsStock_QO goodsStock_qo);
}

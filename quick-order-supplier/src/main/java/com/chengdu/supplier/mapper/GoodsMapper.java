package com.chengdu.supplier.mapper;

import com.chengdu.management.pojo.Goods;
import com.chengdu.management.pojo.SupplierInfo;
import org.apache.ibatis.annotations.Param;

public interface GoodsMapper {
    SupplierInfo selSupplierById(@Param("supplierId") int supplierId);

    int insertGoods(Goods goods);

    int updateGoods(Goods goods);
}

package com.chengdu.qo.rest.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface GoodsStockMapper {
    List<HashMap<String,String>> selStockByDate(@Param("date") String date,
                                               @Param("supplierId") int supplierId);
}

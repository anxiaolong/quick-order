package com.chengdu.management.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GoodsStock_QO implements Serializable {
    private int supplier_id;
    private String goods_name;
    private String goods_sale_date;

    private int pageIndex;
    private int pageSize;

    private int count;

    private List<GoodsStock_VO> goodsStockList;

}

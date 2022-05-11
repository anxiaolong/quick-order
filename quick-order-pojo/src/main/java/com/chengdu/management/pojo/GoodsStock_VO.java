package com.chengdu.management.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsStock_VO implements Serializable {
    private int supplier_id;
    private int id;
    private int goods_id;
    private String goods_name;
    private int goods_count;
    private int goods_price;
    private String goods_sale_date;

}

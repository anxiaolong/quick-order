package com.chengdu.management.pojo;

import lombok.Data;

@Data
public class GoodsStock {
    private int id;
    private int goods_id;
    private int goods_price;
    private int goods_count;
    private String goods_sale_date;

}

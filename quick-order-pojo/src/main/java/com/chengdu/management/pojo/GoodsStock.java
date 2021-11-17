package com.chengdu.management.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsStock implements Serializable {
    private int id;
    private int goods_id;
    private int goods_price;
    private int goods_count;
    private String goods_sale_date;

}

package com.chengdu.management.pojo;

import lombok.Data;

import java.time.LocalDate;
@Data
public class GoodsStock {
    private int id;
    private int goods_id;
    private int goods_price;
    private int goods_count;
    private LocalDate goods_sale_date;

}

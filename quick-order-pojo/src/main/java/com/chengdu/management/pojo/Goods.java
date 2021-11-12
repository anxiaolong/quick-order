package com.chengdu.management.pojo;

import lombok.Data;

@Data
public class Goods {
    private int goods_id;
    private String goods_name;
    private String goods_intro;
    private int goods_supplier_id;
    private int goods_status = 1;
}

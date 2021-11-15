package com.chengdu.management.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Goods_QO {
    private int goods_id;
    private String goods_name;
    private String goods_intro;
    private int goods_supplier_id;
    private int goods_status = 1;

    private int pageSize=5;
    private int pageIndex=1;

    private int count;
    private List<Goods> goodsInfos;
}

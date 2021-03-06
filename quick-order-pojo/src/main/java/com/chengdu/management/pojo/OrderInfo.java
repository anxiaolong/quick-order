package com.chengdu.management.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderInfo implements Serializable {
    private int id;
    private String order_id;
    private int goods_id;
    private int goods_count;
    private int total_price;
    private String uid;
    private String phone;
    private String tips;
    private int status;
    private String saleDate;
    private String create_time;
    private String modified_time;
}

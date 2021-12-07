package com.chengdu.management.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrderInfo_QO implements Serializable {
    private int id;
    private String order_id;
    private int goods_id;
    private int goods_count;
    private int total_price;
    private String uid;
    private String phone;
    private int status;
    private String saleDate;
    private String create_time;
    private String modified_time;

    private int supplierId;

    private int pageSize=5;
    private int pageIndex=1;

    private int count;
    private List<OrderInfo> orderInfos;
}

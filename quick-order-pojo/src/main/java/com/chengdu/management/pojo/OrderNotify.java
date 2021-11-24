package com.chengdu.management.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderNotify implements Serializable {
    private int id;
    private String transaction_id;
    private String order_id;
    private String pay_date;
    private int pay_amount;
    private int pay_status;
}

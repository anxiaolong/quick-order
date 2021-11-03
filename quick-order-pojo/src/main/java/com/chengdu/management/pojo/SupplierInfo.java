package com.chengdu.management.pojo;

import lombok.Data;

@Data
public class SupplierInfo {
    private int supplier_id;
    private String supplier_code;
    private String supplier_name;
    private int supplier_type;
    private String link_man;
    private String phone_number;
    private String bank_name;
    private String bank_account;
    private String address;
    private int supplier_status; //1：正常(默认) 0：禁用
}

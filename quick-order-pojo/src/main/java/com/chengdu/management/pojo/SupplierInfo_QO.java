package com.chengdu.management.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SupplierInfo_QO implements Serializable {
    private String supplier_name;
    private String link_man;
    private String phone_number;
    private String address;

    private int pageSize=5;
    private int pageIndex=1;

    private int count;
    private List<SupplierInfo> supplierInfos;
}

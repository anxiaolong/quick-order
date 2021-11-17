package com.chengdu.management.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class SupplierInfo implements Serializable {
    private int supplier_id;

    @NotBlank(message = "供应商编码不能为空")
    private String supplier_code;

    @NotBlank(message = "供应商名不能为空")
    private String supplier_name;

    private int supplier_type;

    @NotBlank(message = "供应商联系人不能为空")
    private String link_man;

    @NotBlank(message = "供应商联系电话不能为空")
    private String phone_number;

    @NotBlank(message = "供应商开户行不能为空")
    private String bank_name;

    @NotBlank(message = "供应商开户账号不能为空")
    private String bank_account;

    @NotBlank(message = "供应商地址不能为空")
    private String address;
    private int supplier_status; //1：正常(默认) 0：禁用
}

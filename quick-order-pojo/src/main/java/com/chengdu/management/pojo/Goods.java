package com.chengdu.management.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class Goods implements Serializable {
    private int goods_id;
    @NotBlank(message = "商品名不能为空")
    private String goods_name;
    @NotBlank(message = "商品简介不能为空")
    private String goods_intro;
    private int goods_supplier_id;
    private int goods_status = 1;
}

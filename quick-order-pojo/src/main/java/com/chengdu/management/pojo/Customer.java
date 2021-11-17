package com.chengdu.management.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Customer implements Serializable {
    private int id;
    private String uid;
    private String phone;
}

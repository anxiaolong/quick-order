package com.chengdu.management.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ManagementAdmin implements Serializable {
    private int id;
    private String uname;
    private String pwd;
}

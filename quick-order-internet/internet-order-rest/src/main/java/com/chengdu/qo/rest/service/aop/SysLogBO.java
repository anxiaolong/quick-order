package com.chengdu.qo.rest.service.aop;

import lombok.Data;

/**
 * 系统日志对象
 */
@Data
public class SysLogBO {

    private String className;

    private String methodName;

    private String params;

    private Long exeuTime;

    private String remark;

    private String createDate;

    private String returnString;
}

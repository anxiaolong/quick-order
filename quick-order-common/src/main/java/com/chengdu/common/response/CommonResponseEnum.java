package com.chengdu.common.response;

public enum CommonResponseEnum {
    Success("0000","操作成功"),Fail("9999","操作失败");

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private CommonResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

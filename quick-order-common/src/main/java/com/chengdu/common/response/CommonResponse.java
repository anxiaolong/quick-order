package com.chengdu.common.response;

import lombok.Data;

@Data
public class CommonResponse {
    private String resCode;
    private String resMsg;
    private Object data;

    public CommonResponse(CommonResponseEnum resEnum, Object resData) {
        this.resCode = resEnum.getCode();
        this.resMsg = resEnum.getMsg();
        this.data = resData;
    }
}

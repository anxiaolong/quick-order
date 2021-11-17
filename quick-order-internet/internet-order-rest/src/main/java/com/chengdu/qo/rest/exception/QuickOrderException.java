package com.chengdu.qo.rest.exception;

import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class QuickOrderException {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResponse deaException(Exception e){
        e.printStackTrace();
        return new CommonResponse(CommonResponseEnum.Fail,"服务异常，请联系管理管");
    }

    @ResponseBody
    @ExceptionHandler(value = org.springframework.dao.DuplicateKeyException.class)
    public CommonResponse dealSQLIntegrityConstraintViolationException(Exception e){
        e.printStackTrace();
        return new CommonResponse(CommonResponseEnum.Fail,"已存在有相同数据");
    }
}

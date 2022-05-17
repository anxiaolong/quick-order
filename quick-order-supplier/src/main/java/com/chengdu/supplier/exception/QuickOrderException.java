package com.chengdu.supplier.exception;

import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    /**
     * 参数校验异常处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = org.springframework.web.bind.MethodArgumentNotValidException.class)
    public CommonResponse dealMethodArgumentNotValidException(MethodArgumentNotValidException e){
        e.printStackTrace();

        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        int size = fieldErrors.size();
        StringBuilder errMsg = new StringBuilder();

        errMsg.append("Invalid Request：");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                errMsg.append(",");
            }
            FieldError fieldError = fieldErrors.get(i);
            errMsg.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage());
        }

        return new CommonResponse(CommonResponseEnum.Fail,errMsg);
    }
}

package com.chengdu.management.supplier.controller;

import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.management.pojo.SupplierInfo;
import com.chengdu.management.supplier.service.SupplierInfoService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/supplier",produces = "application/json")
public class SupplierInfoController {
    @Resource
    private SupplierInfoService supplierInfoService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public CommonResponse addSupplier(@RequestBody @Valid SupplierInfo supplierInfo, BindingResult result){
        if (result.hasErrors()){
            return new CommonResponse(CommonResponseEnum.Fail,result.getFieldError().getDefaultMessage());
        }
        try {
            if (supplierInfoService.addSupplierInfo(supplierInfo)==1){
                return new CommonResponse(CommonResponseEnum.Success,null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse(CommonResponseEnum.Fail,"录入数据失败，请联系管理员处理");
        }
        return new CommonResponse(CommonResponseEnum.Fail,null);
    }

    @RequestMapping(value = "/update/{supplier_code}",method = RequestMethod.PUT)
    public CommonResponse updateSupplier(@RequestBody SupplierInfo supplierInfo, @PathVariable("supplier_code") String supplier_code){
        if (supplierInfo != null) {
            supplierInfo.setSupplier_code(supplier_code);
            if (supplierInfoService.updateSupplierInfo(supplierInfo)==1){
                return new CommonResponse(CommonResponseEnum.Success,null);
            }
        }
        return new CommonResponse(CommonResponseEnum.Fail,null);
    }
}

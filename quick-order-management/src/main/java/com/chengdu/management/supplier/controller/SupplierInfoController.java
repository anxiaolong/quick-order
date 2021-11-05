package com.chengdu.management.supplier.controller;

import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.management.pojo.SupplierInfo;
import com.chengdu.management.pojo.SupplierInfo_QO;
import com.chengdu.management.supplier.service.SupplierInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (supplierInfoService.addSupplierInfo(supplierInfo)==1){
            return new CommonResponse(CommonResponseEnum.Success,null);
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

    @RequestMapping(value = "/disable/{supplier_code}",method = RequestMethod.PUT)
    public CommonResponse disableSupplier(@RequestBody Map<String,String> map,
                                          @PathVariable("supplier_code") String supplier_code){
        String isDisable = map.get("isDisable");
        int updateStatus = 0;
        if ("true".equals(isDisable)){
            updateStatus = supplierInfoService.updateSupplierStatus(0,supplier_code);
        }
        if ("false".equals(isDisable)){
            updateStatus = supplierInfoService.updateSupplierStatus(1,supplier_code);
        }
        if (updateStatus==1){
            return new CommonResponse(CommonResponseEnum.Success,null);
        }
        return new CommonResponse(CommonResponseEnum.Fail,null);
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public CommonResponse searchSupplierInfo(@RequestBody SupplierInfo_QO supplierInfo_qo){
        supplierInfo_qo.setPageIndex((supplierInfo_qo.getPageIndex()-1)*supplierInfo_qo.getPageSize());
        SupplierInfo_QO supplierInfo_qo1 = supplierInfoService.selSupplierInfo(supplierInfo_qo);
        Map<String,Object> map = new HashMap<>();
        map.put("supplierInfos",supplierInfo_qo1.getSupplierInfos());
        map.put("count",supplierInfo_qo1.getCount());
        return new CommonResponse(CommonResponseEnum.Success,map);
    }

}

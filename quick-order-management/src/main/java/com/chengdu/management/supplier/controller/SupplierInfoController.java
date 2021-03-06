package com.chengdu.management.supplier.controller;

import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.management.aop.SysLog;
import com.chengdu.management.pojo.SupplierInfo;
import com.chengdu.management.pojo.SupplierInfo_QO;
import com.chengdu.management.supplier.service.SupplierInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
@Api(tags = "供应商管理接口")
@RestController
@RequestMapping(value = "/supplier",produces = "application/json")
public class SupplierInfoController {
    @Resource
    private SupplierInfoService supplierInfoService;

    @ApiOperation(value = "新增供应商")
    @SysLog
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

    @ApiOperation(value = "更新供应商信息")
    @SysLog
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

    @ApiOperation(value = "禁用/启用 供应商")
    @SysLog
    @RequestMapping(value = "/disable/{supplier_code}",method = RequestMethod.PUT)
    public CommonResponse disableSupplier(@RequestBody Map<String,Boolean> map,
                                          @PathVariable("supplier_code") String supplier_code){
        boolean isDisable = map.get("isDisable");
        int updateStatus = 0;
        if (isDisable){
            updateStatus = supplierInfoService.updateSupplierStatus(0,supplier_code);
        }else {
            updateStatus = supplierInfoService.updateSupplierStatus(1,supplier_code);
        }

        if (updateStatus==1){
            return new CommonResponse(CommonResponseEnum.Success,null);
        }
        return new CommonResponse(CommonResponseEnum.Fail,null);
    }

    @ApiOperation(value = "分页查询供应商列表")
    @SysLog
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

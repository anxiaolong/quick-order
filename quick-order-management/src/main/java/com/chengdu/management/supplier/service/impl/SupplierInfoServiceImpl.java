package com.chengdu.management.supplier.service.impl;

import com.chengdu.management.aop.SysLog;
import com.chengdu.management.pojo.SupplierInfo;
import com.chengdu.management.pojo.SupplierInfo_QO;
import com.chengdu.management.supplier.mapper.SupplierInfoMapper;
import com.chengdu.management.supplier.service.SupplierInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SupplierInfoServiceImpl implements SupplierInfoService {
    @Resource
    private SupplierInfoMapper supplierInfoMapper;

    @SysLog
    @Override
    public int addSupplierInfo(SupplierInfo supplierInfo) {
        return supplierInfoMapper.insertSupplier(supplierInfo);
    }

    @SysLog
    @Override
    public int updateSupplierInfo(SupplierInfo supplierInfo) {
        return supplierInfoMapper.updateSupplier(supplierInfo);
    }

    @SysLog
    @Override
    public int updateSupplierStatus(int status, String supplier_code) {
        return supplierInfoMapper.updateSupplierStatus(status,supplier_code);
    }

    @SysLog
    @Override
    public SupplierInfo_QO selSupplierInfo(SupplierInfo_QO supplierInfo_qo) {
        List<SupplierInfo> supplierInfos = supplierInfoMapper.selSupplierInfo(supplierInfo_qo);
        int count = supplierInfoMapper.countSupplier(supplierInfo_qo);
        supplierInfo_qo.setCount(count);
        supplierInfo_qo.setSupplierInfos(supplierInfos);
        return supplierInfo_qo;
    }
}

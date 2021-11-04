package com.chengdu.management.supplier.service.impl;

import com.chengdu.management.pojo.SupplierInfo;
import com.chengdu.management.supplier.mapper.SupplierInfoMapper;
import com.chengdu.management.supplier.service.SupplierInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SupplierInfoServiceImpl implements SupplierInfoService {
    @Resource
    private SupplierInfoMapper supplierInfoMapper;

    @Override
    public int addSupplierInfo(SupplierInfo supplierInfo) {
        return supplierInfoMapper.insertSupplier(supplierInfo);
    }

    @Override
    public int updateSupplierInfo(SupplierInfo supplierInfo) {
        return supplierInfoMapper.updateSupplier(supplierInfo);
    }
}

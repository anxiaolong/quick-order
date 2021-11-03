package com.chengdu.management.supplier.service.impl;

import com.chengdu.management.pojo.SupplierInfo;
import com.chengdu.management.supplier.mapper.SupplierInfoMapper;
import com.chengdu.management.supplier.service.SupplierInfoService;

import javax.annotation.Resource;

public class SupplierInfoServiceImpl implements SupplierInfoService {
    @Resource
    private SupplierInfoMapper supplierInfoMapper;

    @Override
    public int addSupplierInfo(SupplierInfo supplierInfo) {
        return 0;
    }
}

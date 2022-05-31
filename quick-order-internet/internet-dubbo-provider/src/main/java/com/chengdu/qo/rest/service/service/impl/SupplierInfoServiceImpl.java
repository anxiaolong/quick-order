package com.chengdu.qo.rest.service.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chengdu.management.pojo.SupplierInfo;
import com.chengdu.qo.rest.service.SupplierInfoService;
import com.chengdu.qo.rest.service.mapper.SupplierInfoMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
@Service
@Component
public class SupplierInfoServiceImpl implements SupplierInfoService {
    @Resource
    private SupplierInfoMapper supplierInfoMapper;

    @Override
    public List<SupplierInfo> selSupplierInfoList() {
        return supplierInfoMapper.selSupplierInfoList();
    }
}

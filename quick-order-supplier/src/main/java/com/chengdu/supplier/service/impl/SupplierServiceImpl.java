package com.chengdu.supplier.service.impl;

import com.chengdu.supplier.mapper.SupplierMapper;
import com.chengdu.supplier.service.SupplierService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Resource
    private SupplierMapper supplierMapper;

    @Override
    public int getLoginVerificationCode(String phoneNumber) {
        if (supplierMapper.selSupplierByPhoneNumber(phoneNumber) != 0) {
            return getVerificationCode();
        }
        return 0;
    }

    private int getVerificationCode() {
        Random random = new Random();
        return random.nextInt(900000)+100000;
    }
}

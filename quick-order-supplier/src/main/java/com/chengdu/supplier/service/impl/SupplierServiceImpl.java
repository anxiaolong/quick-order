package com.chengdu.supplier.service.impl;

import com.chengdu.supplier.mapper.SupplierMapper;
import com.chengdu.supplier.service.SupplierService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Random;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Resource
    private SupplierMapper supplierMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public int getLoginVerificationCode(String phoneNumber) {
        if (supplierMapper.selSupplierByPhoneNumber(phoneNumber) != null) {
            return getVerificationCode();
        }
        return 0;
    }

    @Override
    public boolean supplierLoginService(String phone, String verificationCode) {
        if (supplierMapper.selSupplierByPhoneNumber(phone) != null &&
                Objects.equals(stringRedisTemplate.opsForValue().get(phone), verificationCode)) {
            return true;
        }
        return false;
    }

    private int getVerificationCode() {
        Random random = new Random();
        return random.nextInt(900000)+100000;
    }
}

package com.chengdu.supplier.service;

import com.chengdu.management.pojo.SupplierInfo;

public interface SupplierService {

    int getLoginVerificationCode(String phoneNumber);

    boolean supplierLoginService(String phone,String verificationCode);

    SupplierInfo getSullpierInfoByPhone(String phone);

}

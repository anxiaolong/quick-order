package com.chengdu.supplier.service;

public interface SupplierService {

    int getLoginVerificationCode(String phoneNumber);

    boolean supplierLoginService(String phone,String verificationCode);

}

package com.chengdu.management.supplier.service;

import com.chengdu.management.pojo.SupplierInfo;
import com.chengdu.management.pojo.SupplierInfo_QO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierInfoService {
    int addSupplierInfo(SupplierInfo supplierInfo);

    int updateSupplierInfo(SupplierInfo supplierInfo);

    int updateSupplierStatus(int status,String supplier_code);

    SupplierInfo_QO selSupplierInfo(SupplierInfo_QO supplierInfo_qo);
}

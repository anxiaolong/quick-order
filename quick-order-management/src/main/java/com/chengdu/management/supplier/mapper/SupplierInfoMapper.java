package com.chengdu.management.supplier.mapper;

import com.chengdu.management.pojo.SupplierInfo;
import com.chengdu.management.pojo.SupplierInfo_QO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierInfoMapper {

    int insertSupplier(SupplierInfo supplierInfo);

    int updateSupplier(SupplierInfo supplierInfo);

    int updateSupplierStatus(@Param("status") int status,@Param("supplier_code") String supplier_code);

    List<SupplierInfo> selSupplierInfo(SupplierInfo_QO supplierInfo_qo);

    Integer countSupplier(SupplierInfo_QO supplierInfo_qo);

}

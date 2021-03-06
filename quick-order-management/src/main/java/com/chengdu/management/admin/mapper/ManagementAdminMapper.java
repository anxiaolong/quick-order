package com.chengdu.management.admin.mapper;

import com.chengdu.management.pojo.ManagementAdmin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagementAdminMapper {

    ManagementAdmin selAdminByUnameAndPwd(@Param("uname") String uname,@Param("pwd") String pwd);

    int updPwdById(@Param("id") int id,@Param("pwd") String pwd);

    List<ManagementAdmin> selManagementAdminList();
}

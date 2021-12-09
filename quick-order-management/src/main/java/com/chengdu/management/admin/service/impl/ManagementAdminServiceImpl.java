package com.chengdu.management.admin.service.impl;

import com.chengdu.management.admin.mapper.ManagementAdminMapper;
import com.chengdu.management.admin.service.ManagementAdminService;
import com.chengdu.management.aop.SysLog;
import com.chengdu.management.pojo.ManagementAdmin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ManagementAdminServiceImpl implements ManagementAdminService {
    @Resource
    private ManagementAdminMapper managementAdminMapper;

    @SysLog
    @Override
    public ManagementAdmin managementAdminLogin(String uname, String pwd) {
        return managementAdminMapper.selAdminByUnameAndPwd(uname, pwd);
    }

    @SysLog
    @Override
    public int managementAdminUpdatePwd(int id, String pwd) {
        return managementAdminMapper.updPwdById(id,pwd);
    }
}

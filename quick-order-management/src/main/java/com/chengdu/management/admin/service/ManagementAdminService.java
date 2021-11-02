package com.chengdu.management.admin.service;

import com.chengdu.management.pojo.ManagementAdmin;

public interface ManagementAdminService {
    /**
     * 超管登录
     * @return
     */
    ManagementAdmin managementAdminLogin(String uname,String pwd);

    /**
     * 超管修改密码
     * @param id
     * @param pwd 新密码
     * @return
     */
    int managementAdminUpdatePwd(int id,String pwd);
}

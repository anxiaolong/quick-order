package com.chengdu.management.admin.controller;

import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.management.admin.service.ManagementAdminService;
import com.chengdu.management.pojo.ManagementAdmin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/admin",produces = "application/json")
public class ManagementController {
    @Resource
    private ManagementAdminService managementAdminService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public CommonResponse managementAdminLogin(@RequestBody ManagementAdmin admin, HttpSession session){
        String uname = admin.getUname();
        String pwd = admin.getPwd();
        if (StringUtils.isNotBlank(uname) && StringUtils.isNotBlank(pwd)){
            ManagementAdmin managementAdmin = managementAdminService.managementAdminLogin(uname, pwd);
            if (managementAdmin != null) {
                // 登录成功后开启session
                session.setAttribute("uname",managementAdmin.getUname());
                // 设置session有效期半小时 单位秒
                session.setMaxInactiveInterval(60*30);
                return new CommonResponse(CommonResponseEnum.Success,"登录成功");
            }
        }
        return new CommonResponse(CommonResponseEnum.Fail,"用户名或密码错误！");
    }

    @RequestMapping(value = "/{id}/update",method = RequestMethod.PUT)
    public CommonResponse managementUpdatePwd(@PathVariable("id") int id,@RequestBody ManagementAdmin admin){
        String pwd = admin.getPwd();
        if (id != 0 && StringUtils.isNotBlank(pwd)){
            int update = managementAdminService.managementAdminUpdatePwd(id, pwd);
            if (update==1){
                return new CommonResponse(CommonResponseEnum.Success,"密码修改成功！");
            }
        }
        return new CommonResponse(CommonResponseEnum.Fail,"找不到用户id或新密码");
    }

}

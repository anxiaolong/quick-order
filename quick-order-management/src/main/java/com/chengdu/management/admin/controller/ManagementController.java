package com.chengdu.management.admin.controller;

import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.management.admin.service.ManagementAdminService;
import com.chengdu.management.pojo.ManagementAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Api(tags = "超级管理员服务接口")
@RestController
@RequestMapping(value = "/admin",produces = "application/json")
public class ManagementController {
    @Resource
    private ManagementAdminService managementAdminService;

    @ApiOperation(value = "超级管理员登录",notes = "使用用户名和密码登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public CommonResponse managementAdminLogin(@RequestBody ManagementAdmin admin, HttpSession session){
        String uname = admin.getUname();
        String pwd = admin.getPwd();
        if (StringUtils.isNotBlank(uname) && StringUtils.isNotBlank(pwd)){
            ManagementAdmin managementAdmin = managementAdminService.managementAdminLogin(uname, pwd);
            if (managementAdmin != null) {
                // 登录成功后开启session
                session.setAttribute("uname",managementAdmin.getUname());
                return new CommonResponse(CommonResponseEnum.Success,null);
            }
        }
        return new CommonResponse(CommonResponseEnum.Fail,null);
    }

    @ApiOperation(value = "超级管理员登出",notes = "超级管理员登出")
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public CommonResponse managementAdminLogout(HttpSession session){
        session.invalidate(); // session直接设置失效即可
        return new CommonResponse(CommonResponseEnum.Success,null);
    }

    @ApiOperation(value = "超级管理员修改密码",notes = "修改密码")
    @RequestMapping(value = "/{id}/update",method = RequestMethod.PUT)
    public CommonResponse managementUpdatePwd(@PathVariable("id") int id,@RequestBody ManagementAdmin admin){
        String pwd = admin.getPwd();
        if (id != 0 && StringUtils.isNotBlank(pwd)){
            int update = managementAdminService.managementAdminUpdatePwd(id, pwd);
            if (update==1){
                return new CommonResponse(CommonResponseEnum.Success,null);
            }
        }
        return new CommonResponse(CommonResponseEnum.Fail,null);
    }

    @ApiOperation(value = "获取超级管理员列表",notes = "获取管理员列表")
    @RequestMapping(value = "/list/get",method = RequestMethod.POST)
    public CommonResponse getAminList(){
        List<ManagementAdmin> managementAdminList = managementAdminService.getManagementAdminList();
        return new CommonResponse(CommonResponseEnum.Success,managementAdminList);
    }

}

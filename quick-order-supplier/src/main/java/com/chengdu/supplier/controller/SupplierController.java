package com.chengdu.supplier.controller;

import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.management.pojo.SupplierInfo;
import com.chengdu.supplier.aop.SysLog;
import com.chengdu.supplier.service.SupplierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@Api(tags = "商家服务接口")
@RestController
@RequestMapping(value = "/supplier",produces = "application/json")
public class SupplierController {
    @Resource
    private SupplierService supplierService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value = "获取短信验证码接口",notes = "下发的验证码存redis中，有效期2分钟")
    @SysLog
    @RequestMapping(value = "/getVerificationCode",method = RequestMethod.POST)
    public CommonResponse getVerificationCode(@RequestBody Map<String,String> map){
        String phone = map.get("phone");
        if (stringRedisTemplate.opsForValue().get(phone) != null) {
            return new CommonResponse(CommonResponseEnum.Fail,"短信已下发，请2分钟后再次尝试获取");
        }
        int loginVerificationCode = supplierService.getLoginVerificationCode(phone);
        if (loginVerificationCode != 0){
            stringRedisTemplate.opsForValue().set(phone,loginVerificationCode+"",60*2, TimeUnit.SECONDS);
            System.out.println("发送验证码到" + phone + ":" + loginVerificationCode);
            return new CommonResponse(CommonResponseEnum.Success,null);
        }
        return new CommonResponse(CommonResponseEnum.Fail,"商家手机号输入有误");
    }

    @ApiOperation(value = "商家短信验证码登录接口",notes = "通过短信验证码登录，万能验证码000000")
    @SysLog
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public CommonResponse login(@RequestBody Map<String,String> map, HttpSession session){
        String phone = map.get("phone");
        String verificationCode = map.get("verificationCode");

        SupplierInfo sullpierInfo = supplierService.getSullpierInfoByPhone(phone);

        if (sullpierInfo != null) {
            // 添加一个万能验证码方便测试
            if ("000000".equals(verificationCode)) {
                session.setAttribute("phone",phone);
                return new CommonResponse(CommonResponseEnum.Success,sullpierInfo);
            }

            if (supplierService.supplierLoginService(phone,verificationCode)) {
                session.setAttribute("phone",phone);
                return new CommonResponse(CommonResponseEnum.Success,sullpierInfo);
            }
        }

        return new CommonResponse(CommonResponseEnum.Fail,"没有找到对应的供应商！");
    }



}

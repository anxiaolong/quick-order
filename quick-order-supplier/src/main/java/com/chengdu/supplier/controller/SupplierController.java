package com.chengdu.supplier.controller;

import com.chengdu.common.response.CommonResponse;
import com.chengdu.common.response.CommonResponseEnum;
import com.chengdu.supplier.aop.SysLog;
import com.chengdu.supplier.service.SupplierService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/supplier",produces = "application/json")
public class SupplierController {
    @Resource
    private SupplierService supplierService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

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

    @SysLog
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public CommonResponse login(@RequestBody Map<String,String> map, HttpSession session){
        String phone = map.get("phone");
        String verificationCode = map.get("verificationCode");

        // 添加一个万能验证码方便测试
        if ("000000".equals(verificationCode)) {
            session.setAttribute("phone",phone);
            return new CommonResponse(CommonResponseEnum.Success,null);
        }

        if (supplierService.supplierLoginService(phone,verificationCode)) {
            session.setAttribute("phone",phone);
            return new CommonResponse(CommonResponseEnum.Success,null);
        }
        return new CommonResponse(CommonResponseEnum.Fail,"验证码校验失败");
    }



}

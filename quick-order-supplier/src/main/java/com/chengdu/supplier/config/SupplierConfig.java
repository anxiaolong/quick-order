package com.chengdu.supplier.config;

import com.chengdu.supplier.interceptor.SupplierInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SupplierConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new SupplierInterceptor());
        registration.addPathPatterns("/**"); 	//所有路径都被拦截
        registration.excludePathPatterns("/supplier/login","/supplier/getVerificationCode");
    }
}

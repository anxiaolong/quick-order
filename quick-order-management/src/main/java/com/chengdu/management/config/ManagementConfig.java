package com.chengdu.management.config;

import com.chengdu.management.interceptor.ManagementInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ManagementConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new ManagementInterceptor());
        registration.addPathPatterns("/**"); 	//所有路径都被拦截
        registration.excludePathPatterns("/admin/login");
    }
}

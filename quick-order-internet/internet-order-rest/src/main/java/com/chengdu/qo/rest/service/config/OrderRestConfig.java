package com.chengdu.qo.rest.service.config;

import com.chengdu.qo.rest.service.filter.OrderRestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OrderRestConfig implements WebMvcConfigurer {
    // 注入filter对象，保证filter中注入的service不为空
    @Bean
    public OrderRestFilter getFilter(){
        return new OrderRestFilter();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean  bean = new FilterRegistrationBean(getFilter());
        return bean;
    }
}

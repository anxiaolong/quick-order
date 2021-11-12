package com.chengdu.supplier.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SupplierInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type","application/json");
        try {
            if (StringUtils.isNotBlank(request.getSession().getAttribute("phone").toString())) {
                return true;
            }else {
                response.getWriter().write("{\"msg\":\"请登录后完成操作\"}");
            }
        } catch (Exception e) {
            response.getWriter().write("{\"msg\":\"请登录后完成操作\"}");
        }
        return false;
    }
}

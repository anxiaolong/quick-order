package com.chengdu.management.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ManagementInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if (StringUtils.isNotBlank(request.getSession().getAttribute("uname").toString())) {
                return true;
            }else {
                response.getWriter().write("{'msg':'session verification failed'}");
            }
        } catch (Exception e) {
            response.getWriter().write("{'msg':'session verification failed'}");
        }
        return false;
    }
}

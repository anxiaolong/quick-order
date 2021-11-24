package com.chengdu.qo.rest.service.filter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.chengdu.common.util.CommonSignUtils;
import com.chengdu.qo.rest.service.PartnerKeyService;
import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

public class OrderRestFilter implements Filter {
    @Reference
    private PartnerKeyService partnerKeyService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 释放特定接口
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        if ("/order/notify".equals(requestURI)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("application/json");

        BodyReaderWrapper bodyReaderWrapper = new BodyReaderWrapper((HttpServletRequest) servletRequest);

        String bodyString = IOUtils.toString(bodyReaderWrapper.getInputStream(), "UTF-8");
        // System.out.println(bodyString);

        JSONObject requestJson = JSON.parseObject(bodyString, Feature.OrderedField);
        Integer partner = requestJson.getInteger("partner");
        String key = partnerKeyService.getKeyByPartnerId(partner);
        String sign = requestJson.getString("sign");
        String sign2 = CommonSignUtils.getSign(requestJson.getJSONObject("data").toJSONString(), key);

        if (Objects.equals(sign,sign2)){
            filterChain.doFilter(bodyReaderWrapper,servletResponse);
            return;
        }
        servletResponse.getWriter().write("{\"msg\":\"签名错误\"}");
    }
}

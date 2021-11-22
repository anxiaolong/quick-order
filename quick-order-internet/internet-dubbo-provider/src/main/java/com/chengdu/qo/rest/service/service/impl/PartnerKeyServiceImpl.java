package com.chengdu.qo.rest.service.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chengdu.qo.rest.service.PartnerKeyService;
import com.chengdu.qo.rest.service.mapper.PartnerKeyMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Service
public class PartnerKeyServiceImpl implements PartnerKeyService {
    @Resource
    private PartnerKeyMapper partnerKeyMapper;

    @Override
    public String getKeyByPartnerId(int partnerId) {
        return partnerKeyMapper.selKeyByPartnerId(partnerId);
    }
}

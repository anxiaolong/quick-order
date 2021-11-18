package com.chengdu.qo.rest.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chengdu.qo.rest.mapper.GoodsStockMapper;
import com.chengdu.qo.rest.stock.service.GoodsStockService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Component
public class GoodsStockServiceImpl implements GoodsStockService {
    @Resource
    private GoodsStockMapper goodsStockMapper;

    @Override
    public List<HashMap<String, String>> queryStockByDate(String date, int supplierId) {
        return goodsStockMapper.selStockByDate(date,supplierId);
    }
}

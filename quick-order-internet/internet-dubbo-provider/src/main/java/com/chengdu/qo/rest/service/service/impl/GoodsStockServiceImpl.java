package com.chengdu.qo.rest.service.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chengdu.qo.rest.service.mapper.GoodsStockMapper;
import com.chengdu.qo.rest.service.GoodsStockService;
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

    @Override
    public Integer selCountByGoodsIdAndSaleDate(int goodsId, String saleDate) {
        return goodsStockMapper.selCountByGoodsIdAndSaleDate(goodsId,saleDate);
    }
}

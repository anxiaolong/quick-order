package com.chengdu.supplier.service.impl;

import com.chengdu.management.pojo.Goods;
import com.chengdu.management.pojo.Goods_QO;
import com.chengdu.supplier.mapper.GoodsMapper;
import com.chengdu.supplier.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public int addGoodsService(Goods goods, int supplierId) {
        if (goodsMapper.selSupplierById(supplierId) != null) {
            return goodsMapper.insertGoods(goods);
        }
        return 0;
    }

    @Override
    public int updateGoodsService(Goods goods) {
        return goodsMapper.updateGoods(goods);
    }

    @Override
    public Goods_QO listGoods(Goods_QO goods_qo) {
        List<Goods> goodsList = goodsMapper.listGoods(goods_qo);
        Integer count = goodsMapper.countGoods(goods_qo);
        goods_qo.setGoodsInfos(goodsList);
        goods_qo.setCount(count);
        return goods_qo;
    }
}

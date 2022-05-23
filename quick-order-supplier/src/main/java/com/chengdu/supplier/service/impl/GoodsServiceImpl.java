package com.chengdu.supplier.service.impl;

import com.chengdu.management.pojo.Goods;
import com.chengdu.management.pojo.Goods_QO;
import com.chengdu.supplier.aop.SysLog;
import com.chengdu.supplier.mapper.GoodsMapper;
import com.chengdu.supplier.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    @SysLog
    @Override
    public int addGoodsService(Goods goods, int supplierId) {
        if (goodsMapper.selSupplierById(supplierId) != null) {
            return goodsMapper.insertGoods(goods);
        }
        return 0;
    }

    @SysLog
    @Override
    public int updateGoodsService(Goods goods) {
        return goodsMapper.updateGoods(goods);
    }

    @SysLog
    @Override
    public Goods_QO listGoods(Goods_QO goods_qo) {
        List<Goods> goodsList = goodsMapper.listGoods(goods_qo);
        Integer count = goodsMapper.countGoods(goods_qo);
        goods_qo.setGoodsInfos(goodsList);
        goods_qo.setCount(count);
        return goods_qo;
    }

    @Override
    public int updateGoodsStatus(int goods_id, int goods_status) {
        return goodsMapper.updateGoodsStatus(goods_id,goods_status);
    }

    @Override
    public Goods selGoodsById(int goodsId) {
        return goodsMapper.selGoodsById(goodsId);
    }
}

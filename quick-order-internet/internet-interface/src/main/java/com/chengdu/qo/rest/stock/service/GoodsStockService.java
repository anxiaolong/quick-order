package com.chengdu.qo.rest.stock.service;

import java.util.HashMap;
import java.util.List;

public interface GoodsStockService {
    List<HashMap<String,String>> queryStockByDate(String date, int supplierId);
}

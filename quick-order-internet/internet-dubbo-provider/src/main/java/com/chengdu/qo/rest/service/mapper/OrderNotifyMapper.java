package com.chengdu.qo.rest.service.mapper;

import com.chengdu.management.pojo.OrderNotify;
import org.apache.ibatis.annotations.Select;

public interface OrderNotifyMapper {
    Integer insertOrderNotify(OrderNotify orderNotify);

    @Select("select sum(qon.pay_amount) from qo_order_notify qon where" +
            " qon.order_id = #{order_id} and qon.pay_status = 0;")
    Integer selPayAmountAll(OrderNotify orderNotify);
}

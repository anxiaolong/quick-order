package com.chengdu.qo.rest.service.mapper;

import com.chengdu.management.pojo.OrderInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface OrderInfoMapper {
    Integer insertOrderInfo(OrderInfo orderInfo);

    List<OrderInfo> selTimeOutOrder();

    Integer updateOrderStatus(OrderInfo orderInfo);

    @Select("select total_price from qo_order_info where" +
            " order_id = #{orderId} and status = 0")
    Integer selTotalPriceByOrderId(@Param("orderId") String orderId);

    OrderInfo selOrderInfoByOrderInfo(OrderInfo orderInfo);

    List<Map<String,Object>> selOrderInfoByPhone(@Param("phone") String phone);

}

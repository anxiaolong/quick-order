package com.chengdu.qo.rest.service.scheduled;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chengdu.qo.rest.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderScheduled {
    @Reference
    private OrderService orderService;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void orderTimeoutTask(){
        System.out.println("订单过期检查定时任务:开始执行");
        orderService.orderTimeoutService();
        System.out.println("订单过期检查定时任务:执行完成");
    }
}

package com.chengdu.qo.scheduled;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chengdu.qo.rest.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderScheduled {
    @Reference
    private OrderService orderService;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void orderTimeoutTask(){
        log.info("订单过期检查定时任务:开始执行");
        orderService.orderTimeoutService();
        log.info("订单过期检查定时任务:执行完成");
    }
}

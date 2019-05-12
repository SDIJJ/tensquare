package com.tensquare_rabbitmq_test.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: rabbitmq fanout 模式测试
 * @author: Arnold
 * @since: 2019/5/11 15:34
 * @version: v1.0.0
 */
@Component
@RabbitListener(queues = "queue3")
public class Customer_3 {
    @RabbitHandler
    public void getMsg(Map map) {
        System.out.println("queue_3"+map + " " + System.currentTimeMillis() / 1000);
    }
}

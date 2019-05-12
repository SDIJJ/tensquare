package com.tensquare_rabbitmq_test.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/11 15:17
 * @version: v1.0.0
 */
@RabbitListener(queues = "tongtong")
@Component
public class CustomerTest {
    @RabbitHandler
    public void getMsg(Map map) {
        System.out.println(map);
    }
}

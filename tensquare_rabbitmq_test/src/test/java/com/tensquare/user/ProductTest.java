package com.tensquare.user;

import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: Arnold
 * @since: 2019/5/11 14:56
 * @version: v1.0.0
 */
public class ProductTest extends ApplicationTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendMsg() {
        int count = 0;

        while (true) {
            Map<String, String> map = new HashMap<>();
            map.put("钢铁侠", "I am Iron Man " + count++);
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            } finally {

            }
            rabbitTemplate.convertAndSend("tongtong", map);
        }
    }

    /**
     * 分列模式测试
     *
     * @throws Exception
     */
    @Test
    public void sendMsgByFanout() throws Exception {
        int count = 0;
        while (true) {
            Map<String, String> map = new HashMap<>();
            map.put("钢铁侠", "I am Iron Man " + count++);
            Thread.sleep(50);
            rabbitTemplate.convertAndSend("exchanges1", "", map);
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void sendMsgByTopic() throws Exception {
        int count = 0;

        int n = 10;
        for (int i = 1; i <= 1; i++) {
        Map<String, String> map = new HashMap<>();
        map.put("Iron", "I am Iron man" + count++);
        Thread.sleep(100);
        rabbitTemplate.convertAndSend("exchanges_topic", "good.log", map);
        rabbitTemplate.convertAndSend("exchanges_topic", "good.abc", map);
        rabbitTemplate.convertAndSend("exchanges_topic", "abc.log", map);

    }

    }
}

package org.victor.test;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.victor.test.POJO.TestBooleanPojo;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class RabbitmqTest {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void contextLoad() {

    }

    @Test
    public void create() {
        amqpAdmin.declareExchange(new DirectExchange("exchange.direct"));
        amqpAdmin.declareExchange(new FanoutExchange("exchange.fanout"));
        amqpAdmin.declareExchange(new TopicExchange("exchange.topic"));

        amqpAdmin.declareQueue(new Queue("direct.queue", true));
        amqpAdmin.declareQueue(new Queue("fanout.queue", true));

        amqpAdmin.declareBinding(new Binding("direct.queue", Binding.DestinationType.QUEUE, "exchange.direct", "direct.queue", null));
        amqpAdmin.declareBinding(new Binding("fanout.queue", Binding.DestinationType.QUEUE, "exchange.direct", "fanout.queue", null));
        amqpAdmin.declareBinding(new Binding("direct.queue", Binding.DestinationType.QUEUE, "exchange.fanout", "", null));
        amqpAdmin.declareBinding(new Binding("fanout.queue", Binding.DestinationType.QUEUE, "exchange.fanout", "", null));
        amqpAdmin.declareBinding(new Binding("direct.queue", Binding.DestinationType.QUEUE, "exchange.topic", "direct.#", null));
        amqpAdmin.declareBinding(new Binding("fanout.queue", Binding.DestinationType.QUEUE, "exchange.topic", "direct.*", null));
    }

    @Test
    public void send2Direct() {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "Direct Message");
        map.put("data", TestBooleanPojo.create());

        rabbitTemplate.convertAndSend("exchange.direct", "direct.queue", map);
    }


    @Test
    public void send2Topic() {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "BoardCast Message");
        map.put("data", TestBooleanPojo.create());

        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("exchange.fanout", "", map);
        }

    }

    @Test
    public void receive() {
        Object o = rabbitTemplate.receiveAndConvert("direct.queue");
        Assert.assertNotNull(o);
        log.info(o.getClass().toGenericString());
        if (o instanceof HashMap) {
            Object data = ((HashMap) o).get("data");
            log.info(data.toString());
            log.info(data.getClass().toGenericString());
        }
    }
}

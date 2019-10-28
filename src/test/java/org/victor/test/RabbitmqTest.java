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

    @Test
    public void send2Direct() {
        rabbitTemplate.convertAndSend(TestConstant.EXCHANGE_NAME, TestConstant.DIRECT_QUEUE, TestBooleanPojo.create());
    }

}

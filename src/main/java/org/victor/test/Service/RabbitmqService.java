package org.victor.test.Service;

import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.victor.test.POJO.ITest;
import org.victor.test.TestConstant;


@Slf4j
@Service
@AllArgsConstructor
public class RabbitmqService {

    RabbitTemplate rabbitTemplate;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = TestConstant.DIRECT_QUEUE, durable = "true", autoDelete = "true"),
            exchange = @Exchange(value = TestConstant.EXCHANGE_NAME),
            key = TestConstant.DIRECT_QUEUE
    ))
    public void receiveDirect(ITest test) {
        log.info("Receive direct queue message data: " + test.getData());
        String routingKey = test.getDataType().toLowerCase() + "." + TestConstant.DIRECT_QUEUE;
        rabbitTemplate.convertAndSend(TestConstant.EXCHANGE_NAME, routingKey, test);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "string." + TestConstant.DIRECT_QUEUE, durable = "true", autoDelete = "true"),
            exchange = @Exchange(value = TestConstant.EXCHANGE_NAME),
            key = "string." + TestConstant.DIRECT_QUEUE
    ))
    public void receiveString(ITest test) {
        log.info("Receive string direct queue message data: " + test.getData());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "boolean." + TestConstant.DIRECT_QUEUE, durable = "true", autoDelete = "true"),
            exchange = @Exchange(value = TestConstant.EXCHANGE_NAME),
            key = "boolean." + TestConstant.DIRECT_QUEUE
    ))
    public void receiveBoolean(ITest test) {
        log.info("Receive boolean direct queue message data: " + test.getData());
    }
}

package org.victor.test.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.victor.test.POJO.ITest;

@Slf4j
@Service
public class RabbitmqService {

    @RabbitListener(queues = "fanout.queue")
    public void receiveFanout(ITest test) {
        log.info("Receive fanout queue message data: " + test.getData());
    }

    @RabbitListener(queues = "direct.queue")
    public void receiveDirect(Message message) {
        log.info("Receive direct queue Message: " + message.toString());
    }
}

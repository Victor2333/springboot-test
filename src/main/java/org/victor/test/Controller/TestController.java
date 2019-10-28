package org.victor.test.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.victor.test.POJO.ITest;
import org.victor.test.TestConstant;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class TestController {

    RabbitTemplate rabbitTemplate;

    @PostMapping("/hello")
    public Object hello(@RequestBody ITest testAbstract) {
        log.info(testAbstract.toString());

        for (int i = 0; i < 100000; i++) {
            rabbitTemplate.convertAndSend(TestConstant.EXCHANGE_NAME, TestConstant.DIRECT_QUEUE, testAbstract);
        }
        return testAbstract.getData();
    }
}

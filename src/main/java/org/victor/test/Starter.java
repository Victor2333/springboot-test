package org.victor.test;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is for spring controller jackson json parser by interface test.
 * It work fine!
 */
@EnableRabbit
@SpringBootApplication
public class Starter {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Starter.class);
        springApplication.run(args);
    }
}

package com.example.demo.handler;

import com.example.demo.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = AppConfig.NAME_HELLO_QUEUE)
@Slf4j
public class Receiver {

    @RabbitHandler
    public void process(String msg) {
        log.info("[-Receiver-].process:msg={}", msg);
    }
}

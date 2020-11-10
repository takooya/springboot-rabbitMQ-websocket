package com.example.demo.handler;

import java.util.Calendar;

import com.example.demo.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Sender {

    @Autowired
    private RabbitTemplate amqpTemplate;

    public void send() {
        String msg = "hello" + Calendar.getInstance().getTimeInMillis();
        log.info("[-Sender-].send:msg={}", msg);
        this.amqpTemplate.convertAndSend(AppConfig.ROUTING_KEY_HELLO, msg);
    }
}

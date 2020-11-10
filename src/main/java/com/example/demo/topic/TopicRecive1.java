package com.example.demo.topic;

import com.example.demo.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = AppConfig.ROUTING_KEY_MSG)
@Slf4j
public class TopicRecive1 {
    @RabbitHandler
    public void process(String info) {
        log.info("[-TopicRecive1-].process:info={}", info);
    }
}

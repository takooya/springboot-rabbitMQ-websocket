package com.example.demo.direct;

import com.example.demo.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DirectSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDirect() {
        for (int i = 0; i < 100; i++) {
            String msg = "direct msg22 " + i;
            log.info("[-DirectSender-].sendDirect:msg={}", msg);
            rabbitTemplate.convertAndSend(AppConfig.DIRECT_EXCHANGE, AppConfig.ROUTING_KEY_MAIN, msg);
        }
    }
}

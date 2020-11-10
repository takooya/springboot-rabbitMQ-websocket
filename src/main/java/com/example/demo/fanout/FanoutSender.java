package com.example.demo.fanout;

import com.example.demo.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;

@Component
@Slf4j
public class FanoutSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        User userInfo = new User(1, "sfsa111", 23);
        log.info("[-FanoutSender-].send:userInfo={}", userInfo);
        this.rabbitTemplate.convertAndSend(AppConfig.FANOUT_EXCHANGE, "", userInfo);
    }

    public void webSocketSend(String msg) {
        this.rabbitTemplate.convertAndSend(AppConfig.FANOUT_EXCHANGE, "", msg);
    }
}

package com.example.demo.fanout;

import com.example.demo.config.AppConfig;
import com.example.demo.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Consumer;

@Component
@RabbitListener(queues = AppConfig.FANOUT_QUEUE_1)
@Slf4j
public class FanoutReciver1 {

    @RabbitHandler
    public void read(String info) {
        log.info("[-FanoutReciver1-].read:info={}", info);
        WebSocketServer.webSockets.forEach(webSocketServer -> {
            try {
                webSocketServer.getSession().getBasicRemote().sendText("FanoutReciver1:" + info);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

package com.example.demo.fanout;

import com.example.demo.config.AppConfig;
import com.example.demo.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.websocket.RemoteEndpoint;
import java.io.IOException;

@Component
@RabbitListener(queues = AppConfig.FANOUT_QUEUE_2)
@Slf4j
public class FanoutReciver2 {

    @RabbitHandler
    public void read(String info) {
        log.info("[-FanoutReciver2-].read:info={}", info);
        WebSocketServer.webSockets.forEach(webSocketServer -> {
            try {
                RemoteEndpoint.Basic wsSession = webSocketServer.getSession().getBasicRemote();
                synchronized (wsSession) {
                    wsSession.sendText("FanoutReciver2:" + info);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

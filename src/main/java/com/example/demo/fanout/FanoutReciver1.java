package com.example.demo.fanout;

import com.example.demo.websocket.WebSocketServer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanoutQueue1")
public class FanoutReciver1 {

//    @RabbitHandler
//    public void read(String user) {
//        System.out.println("fanoutQueue1: " + user);
//    }

    @RabbitHandler
    public void read(String user) {
        System.out.println("fanoutQueue1 接收消息");
        for (WebSocketServer webSocketServer : WebSocketServer.webSockets) {
            try {
                webSocketServer.send(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

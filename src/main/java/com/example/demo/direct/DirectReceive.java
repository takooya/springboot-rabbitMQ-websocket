package com.example.demo.direct;

import com.example.demo.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.websocket.WebSocketServer;

@Component
@RabbitListener(queues = AppConfig.NAME_DIRECT_QUEUE)
@Slf4j
public class DirectReceive {

    @RabbitHandler
    public void process(String msg) throws InterruptedException {
        log.info("[-DirectReceive-].process:msg={}", msg);
        Thread.sleep(500);
        //同websocket推送到页面
//		for(WebSocketServer webSocketServer :WebSocketServer.webSockets){
//            try {
//                webSocketServer.send(msg);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
}

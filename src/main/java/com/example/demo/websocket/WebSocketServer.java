package com.example.demo.websocket;

import com.example.demo.fanout.FanoutSender;
import com.example.demo.util.SpringUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/webSocket")
@Component
@Slf4j
public class WebSocketServer {

    @Autowired
    private FanoutSender fanoutSender;

    public WebSocketServer() {
        if (this.fanoutSender == null) {
            this.fanoutSender = (FanoutSender) SpringUtil.getBean("fanoutSender");
        }
    }

    @Getter
    private Session session;
    public static CopyOnWriteArraySet<WebSocketServer> webSockets = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        fanoutSender.webSocketSend("新用户加入");
    }

    @OnClose
    public void onClose(Session s) {
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        webSockets.remove(this);
        fanoutSender.webSocketSend("有用户离开");
    }

    @OnMessage
    public void onMessage(String msg) {
        log.info("[-WebSocketServer-].onMessage:msg={}", msg);
        fanoutSender.webSocketSend(msg);
    }

    @SuppressWarnings("AliControlFlowStatementWithoutBraces")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebSocketServer that = (WebSocketServer) o;
        return Objects.equals(session, that.session);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session);
    }
}

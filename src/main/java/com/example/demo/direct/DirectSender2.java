package com.example.demo.direct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectSender2 {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendDirect() {
		for(int i=0;i<100;i++) {
			String msg = "direct msg22 "+i;
			System.out.println("Sender2发送的消息： "+msg);
			rabbitTemplate.convertAndSend("directExchange2", "rabbit.msg", msg);
		}
	}
	
}

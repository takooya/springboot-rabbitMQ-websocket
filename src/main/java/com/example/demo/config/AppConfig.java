package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class AppConfig {

    public static final String ROUTING_KEY_MAIN = "rabbit.msg";
    public static final String ROUTING_KEY_MSG = "rabbit.msg.others";
    public static final String ROUTING_KEY_USER = "rabbit.user.others";
    public static final String ROUTING_KEY_HELLO = "hello";

    public static final String NAME_HELLO_QUEUE = "hello";
    public static final String NAME_DIRECT_QUEUE = "direct";
    public static final String FANOUT_QUEUE_1 = "fanoutQueue1";
    public static final String FANOUT_QUEUE_2 = "fanoutQueue2";
    public static final String FANOUT_QUEUE_3 = "fanoutQueue3";

    public static final String DIRECT_EXCHANGE = "directExchange2";
    private static final String TOPIC_EXCHANGE = "topicExchange3";
    public static final String FANOUT_EXCHANGE = "fanoutExchange2";

    @Bean
    public Queue helloQueue() {
        return new Queue(NAME_HELLO_QUEUE);
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /**
     * 如果exchange和queue都是持久化的,那么它们之间的binding也是持久化的,
     * 如果exchange和queue两者之间有一个持久化，一个非持久化,则不允许建立绑定.
     * 注意：一旦创建了队列和交换机,就不能修改其标志了,
     * 例如,创建了一个non-durable的队列,然后想把它改变成durable的,唯一的办法就是删除这个队列然后重现创建。
     *
     * @return Simple container collecting information to describe a queue. Used in conjunction with AmqpAdmin.
     */
    @Bean
    public Queue directQueue() {
        return new Queue(NAME_DIRECT_QUEUE, false);
    }

    @Bean
    public DirectExchange directExchange2() {
        return new DirectExchange(DIRECT_EXCHANGE, false, false);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(directQueue()).to(directExchange2()).with(ROUTING_KEY_MAIN);
    }

    //===============================================================================================

    /**
     * 两个queue绑定在一个TopicExchange
     *
     * @return Simple container collecting information to describe a topic exchange.
     */
    @Bean
    public TopicExchange topicExchange2() {
        return new TopicExchange(TOPIC_EXCHANGE, false, false);
    }

    @Bean
    public Queue msgQueue() {
        return new Queue(AppConfig.ROUTING_KEY_MSG, false);
    }

    @Bean
    public Binding bindMsgQueue2() {
        return BindingBuilder.bind(msgQueue()).to(topicExchange2()).with(ROUTING_KEY_MSG);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(AppConfig.ROUTING_KEY_USER, false);
    }

    @Bean
    public Binding bindUserQueue2() {
        return BindingBuilder.bind(userQueue()).to(topicExchange2()).with(ROUTING_KEY_USER);
    }

    //===============================================================================================

    @Bean
    public Queue fanoutQueue1() {
        return new Queue(FANOUT_QUEUE_1, false);
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue(FANOUT_QUEUE_2, false);
    }

    @Bean
    public Queue fanoutQueue3() {
        return new Queue(FANOUT_QUEUE_3, false);
    }

    @Bean
    public FanoutExchange fanoutExchange2() {
        return new FanoutExchange(FANOUT_EXCHANGE, false, false);
    }

    @Bean
    public Binding bindFanoutQueue1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange2());
    }

    @Bean
    public Binding bindFanoutQueue2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange2());
    }

    @Bean
    public Binding bindFanoutQueue3() {
        return BindingBuilder.bind(fanoutQueue3()).to(fanoutExchange2());
    }
}

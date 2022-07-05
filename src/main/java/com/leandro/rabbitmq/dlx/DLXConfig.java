package com.leandro.rabbitmq.dlx;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DLXConfig {

    private static final String NAME_EXCHANGE_DLX = "topicExchangeDLX";
    private static final String NAME_QUEUE_DLX = "queue_topic_dlx";
    private static final String ROUTING_KEY_DLX = "key.dlx";

    private static final String NAME_EXCHANGE = "topicExchangeNotDLX";
    private static final String NAME_QUEUE = "queue_topic_not_dlx";
    private static final String ROUTING_KEY = "key.not.dlx";

    private static final String URI_AMQP = "amqps://rmlkqywa:UCjDND1gVH35rRZGIjVvtvaxv5BwhZwsY@beaver.rmq.cloudamqp.com/rmlkqywa";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(URI_AMQP);

        try(Connection connection = factory.newConnection()) {

            Channel channel = connection.createChannel();

            channel.exchangeDeclare(NAME_EXCHANGE_DLX, BuiltinExchangeType.TOPIC);
            channel.exchangeDeclare(NAME_EXCHANGE, BuiltinExchangeType.TOPIC);

            Map<String, Object> map = new HashMap<>();
            map.put("x-message-ttl", 10000);
            map.put("x-dead-letter-exchange", NAME_EXCHANGE_DLX);
            map.put("x-dead-letter-routing-key", ROUTING_KEY_DLX);

            channel.queueDeclare(NAME_QUEUE_DLX, false, false, false, null);
            channel.queueDeclare(NAME_QUEUE, false, false, false, map);

            channel.queueBind(NAME_QUEUE_DLX, NAME_EXCHANGE_DLX, ROUTING_KEY_DLX + ".#");
            channel.queueBind(NAME_QUEUE, NAME_EXCHANGE, ROUTING_KEY + ".#");

            log.info("Done");
        }

    }
}
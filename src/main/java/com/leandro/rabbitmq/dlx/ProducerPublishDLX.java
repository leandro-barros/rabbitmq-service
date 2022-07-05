package com.leandro.rabbitmq.dlx;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProducerPublishDLX {

    private static final String NAME_EXCHANGE = "topicExchangeNotDLX";

    private static final String ROUTING_KEY = "key.not.dlx.teste";

    private static final String URI_AMQP = "amqps://rmlkqywa:UCjDND1gVH5rRZGIjVvtvaxv35BwhZwsY@beaver.rmq.cloudamqp.com/rmlkqywa";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(URI_AMQP);

        try(Connection connection = factory.newConnection()) {

            Channel channel = connection.createChannel();

            channel.exchangeDeclare(NAME_EXCHANGE, BuiltinExchangeType.TOPIC);

            String message =  "This is my message";

            channel.basicPublish(NAME_EXCHANGE, ROUTING_KEY, null, message.getBytes());

            log.info("Done");
        }

    }
}
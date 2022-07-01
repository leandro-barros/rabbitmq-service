package com.leandro.rabbitmq.exchangetopic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SenderTopic {

    private static final String NAME_EXCHANGE = "topicExchange";

    private static final String ROUTING_KEY_TOPIC = "quick.orange.rabbit";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUri("amqps://rmlkqywa:UCjDND1gVH5rRZGIjVvtvaxv35BwhZwsY@beaver.rmq.cloudamqp.com/rmlkqywa");

        try(Connection connection = factory.newConnection()) {

            Channel channel = connection.createChannel();

            channel.exchangeDeclare(NAME_EXCHANGE, BuiltinExchangeType.TOPIC);

            String message = "Message published in the type Topic";

            channel.basicPublish(NAME_EXCHANGE, ROUTING_KEY_TOPIC, null, message.getBytes());

        }

    }
}
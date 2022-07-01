package com.leandro.rabbitmq.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SenderPubSub {

    private static final String NAME_EXCHANGE = "fanoutExchange";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUri("amqps://rmlkqywa:UCjDND1gVH5rRZGIjVvtvaxv35BwhZwsY@beaver.rmq.cloudamqp.com/rmlkqywa");

        try(Connection connection = factory.newConnection()) {

            Channel channel = connection.createChannel();

            channel.exchangeDeclare(NAME_EXCHANGE, "fanout");

            String message = "Hello this is a pub/sub system";

            channel.basicPublish(NAME_EXCHANGE, "", null, message.getBytes());
        }

    }
}
package com.leandro.rabbitmq.routingkey;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SenderRoutingKey {

    private static final String NAME_EXCHANGE = "directExchange";

    private static final String ROUTING_KEY = "routingKeyTest";

    private static final String SECOND_ROUTING_KEY = "secondRoutingKeyTest";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUri("amqps://rmlkqywa:UCjDND1gVH35rRZGIjVvtvaxv5BwhZwsY@beaver.rmq.cloudamqp.com/rmlkqywa");

        try(Connection connection = factory.newConnection()) {

            Channel channel = connection.createChannel();

            channel.exchangeDeclare(NAME_EXCHANGE, "direct");

            String message = "Message published in the type direct";

            String message2 = "Message second with others Routing Key";

            channel.basicPublish(NAME_EXCHANGE, ROUTING_KEY, null, message.getBytes());

            channel.basicPublish(NAME_EXCHANGE, SECOND_ROUTING_KEY, null, message2.getBytes());
        }

    }
}
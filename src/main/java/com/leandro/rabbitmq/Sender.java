package com.leandro.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {

    private static final String NAME_QUEUE = "HELLO";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
//        factory.setUsername("rmlkqywa:rmlkqywa");
//        factory.setPassword("UCjDND1gVH5rRZGIjVvtvaxv5BwhZwsY");
//        factory.setHost("beaver.rmq.cloudamqp.com");
//        factory.setHost("rmlkqywa");
//        factory.setPort(1883);

        factory.setUri("amqps://rmlkqywa:UCjDND1gVH5rRZGIjVvtvaxv5BwhZwsY@beaver.rmq.cloudamqp.com/rmlkqywa");

        try(Connection connection = factory.newConnection()) {

            Channel channel = connection.createChannel();

            channel.queueDeclare(NAME_QUEUE, false, false, false, null);

            String message = "Hello";

            channel.basicPublish("", NAME_QUEUE, null, message.getBytes());
        }

    }
}
package com.leandro.rabbitmq.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SenderWork {

    private static final String NAME_QUEUE = "Work";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUri("amqps://rmlkqywa:UCjDND1gVH5rRZGIjVvtvaxv5BwhZwsY@beaver.rmq.cloudamqp.com/rmlkqywa");

        try(Connection connection = factory.newConnection()) {

            Channel channel = connection.createChannel();

            channel.queueDeclare(NAME_QUEUE, false, false, false, null);

            String message = "......";

            channel.basicPublish("", NAME_QUEUE, null, message.getBytes());
        }

    }
}
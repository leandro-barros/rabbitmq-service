package com.leandro.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Receiver {

    private static final String NAME_QUEUE = "HELLO";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://rmlkqywa:UCjDND1gVH5rRZGIjVvtvaxv5BwhZwsY@beaver.rmq.cloudamqp.com/rmlkqywa");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(NAME_QUEUE, false, false, false, null);

        DeliverCallback deliverCallback = (ConsumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("[**] Received message: " + message);
        };

        channel.basicConsume(NAME_QUEUE, true, deliverCallback, ConsumerTag -> {});
    }
}

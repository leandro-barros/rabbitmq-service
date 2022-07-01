package com.leandro.rabbitmq.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class SecondReceiverPubSub {

    private static final String NAME_QUEUE = "fanout-broadcast";

    private static final String NAME_EXCHANGE = "fanoutExchange";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://rmlkqywa:UCjDND1gVH5rRZGIjVvtvaxv35BwhZwsY@beaver.rmq.cloudamqp.com/rmlkqywa");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(NAME_QUEUE, false, false, false, null);

        channel.exchangeDeclare(NAME_EXCHANGE, "fanout");

        channel.queueBind(NAME_QUEUE, NAME_EXCHANGE, "");

        DeliverCallback deliverCallback = (ConsumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("[**] Received message: " + message);

        };

        boolean autoack = true;
        channel.basicConsume(NAME_QUEUE, autoack, deliverCallback, ConsumerTag -> {});
    }

}

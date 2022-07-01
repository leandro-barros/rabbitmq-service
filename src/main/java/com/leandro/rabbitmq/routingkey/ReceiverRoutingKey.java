package com.leandro.rabbitmq.routingkey;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ReceiverRoutingKey {

    private static final String NAME_EXCHANGE = "directExchange";

    private static final String NAME_BINDKEY = "routingKeyTest";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://rmlkqywa:UCjDND1gVH5rRZGIjVvtvaxv35BwhZwsY@beaver.rmq.cloudamqp.com/rmlkqywa");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        // Irá criar uma fila aleatório.
        String nameQueue = channel.queueDeclare().getQueue();

        channel.exchangeDeclare(NAME_EXCHANGE, "direct");

        channel.queueBind(nameQueue, NAME_EXCHANGE, NAME_BINDKEY);

        DeliverCallback deliverCallback = (ConsumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("[**] Received message: " + message);

        };

        boolean autoack = true;
        channel.basicConsume(nameQueue, autoack, deliverCallback, ConsumerTag -> {});
    }

}

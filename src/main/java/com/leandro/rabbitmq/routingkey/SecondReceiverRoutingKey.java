package com.leandro.rabbitmq.routingkey;

import com.rabbitmq.client.*;

public class SecondReceiverRoutingKey {

    private static final String NAME_EXCHANGE = "directExchange";

    private static final String NAME_BINDKEY = "secondRoutingKeyTest";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://rmlkqywa:UCjDND1gVH35rRZGIjVvtvaxv5BwhZwsY@beaver.rmq.cloudamqp.com/rmlkqywa");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        // Irá criar uma fila aleatório.
        String nameQueue = channel.queueDeclare().getQueue();

        channel.exchangeDeclare(NAME_EXCHANGE, BuiltinExchangeType.DIRECT);

        channel.queueBind(nameQueue, NAME_EXCHANGE, NAME_BINDKEY);

        DeliverCallback deliverCallback = (ConsumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("[**] Received message: " + message);

        };

        boolean autoack = true;
        channel.basicConsume(nameQueue, autoack, deliverCallback, ConsumerTag -> {});
    }

}

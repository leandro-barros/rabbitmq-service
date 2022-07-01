package com.leandro.rabbitmq.exchangetopic;

import com.rabbitmq.client.*;

public class ReceiverTopic {

    private static final String NAME_EXCHANGE = "topicExchange";

    private static final String NAME_BINDKEY = "*.*.rabbit";

    private static final String NAME_QUEUE = "queue_topic_first";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://rmlkqywa:UCjDND1gVH5rRZGIjVvtvaxv35BwhZwsY@beaver.rmq.cloudamqp.com/rmlkqywa");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(NAME_QUEUE, false, false, false, null);

//        channel.exchangeDeclare(NAME_EXCHANGE, BuiltinExchangeType.TOPIC);

        channel.queueBind(NAME_QUEUE, NAME_EXCHANGE, NAME_BINDKEY);

        DeliverCallback deliverCallback = (ConsumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("[**] Received message Exchange Topic with option (*): " + message);

        };

        boolean autoack = true;
        channel.basicConsume(NAME_QUEUE, autoack, deliverCallback, ConsumerTag -> {});
    }

}

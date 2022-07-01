package com.leandro.rabbitmq.exchangetopic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class SecondReceiverTopic {

    private static final String NAME_EXCHANGE = "topicExchange";

    // Caso coloque somente # todos as mesagens irá cair na fila e funcionará como, por exemplo, a fanout
    private static final String NAME_BINDKEY = "#.rabbit";

    private static final String NAME_QUEUE = "queue_topic_two";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://rmlkqywa:UCjDND1gVH5rRZGIjVvtvaxv35BwhZwsY@beaver.rmq.cloudamqp.com/rmlkqywa");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(NAME_QUEUE, false, false, false, null);

        channel.queueBind(NAME_QUEUE, NAME_EXCHANGE, NAME_BINDKEY);

        DeliverCallback deliverCallback = (ConsumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("[**] Received message Exchange Topic with option (#): " + message);

        };

        boolean autoack = true;
        channel.basicConsume(NAME_QUEUE, autoack, deliverCallback, ConsumerTag -> {});
    }

}

package com.leandro.rabbitmq.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class SecondReceiverWork {

    private static final String NAME_QUEUE = "Work";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://rmlkqywa:UCjDND1gVH5rRZGIjVvtvaxv5BwhZwsY@beaver.rmq.cloudamqp.com/rmlkqywa");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(NAME_QUEUE, false, false, false, null);

        DeliverCallback deliverCallback = (ConsumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("[**] Received message: " + message);

            try {
                doWork(message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println("Done");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };

        boolean autoack = false; // ack is on

        channel.basicConsume(NAME_QUEUE, autoack, deliverCallback, ConsumerTag -> {});
    }

    private static void doWork(String args) throws InterruptedException {
        for (char ch : args.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }
}

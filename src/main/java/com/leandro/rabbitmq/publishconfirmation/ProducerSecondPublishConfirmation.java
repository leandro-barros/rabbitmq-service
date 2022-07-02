package com.leandro.rabbitmq.publishconfirmation;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;

@Slf4j
public class ProducerSecondPublishConfirmation {

    private static final String NAME_EXCHANGE = "topicExchange";

    private static final String ROUTING_KEY_TOPIC = "quick.orange.rabbit";

    private static final String URI_AMQP = "amqps://rmlkqywa:UCjDND1gVH5rRZGIjVvtvaxv35BwhZwsY@beaver.rmq.cloudamqp.com/rmlkqywa";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUri(URI_AMQP);

        try(Connection connection = factory.newConnection()) {

            Channel channel = connection.createChannel();

            AMQP.Confirm.SelectOk selectOk = channel.confirmSelect();

            channel.exchangeDeclare(NAME_EXCHANGE, BuiltinExchangeType.TOPIC);

            Vector<String> vector = new Vector<>(3);
            vector.add("Message 1");
            vector.add("Message 2");
            vector.add("Message 3");

            for (int i = 0; i < 3; i++) {
                String body = vector.get(i);
                channel.basicPublish(NAME_EXCHANGE, ROUTING_KEY_TOPIC, null, body.getBytes());
                log.info("[*] Sending the message: {}", body);

                channel.waitForConfirmsOrDie(5000);

                log.info("* Message confirmed");
            }

            log.info("Done");
        }

    }
}
package com.leandro.rabbitmq.publishconfirmation;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProducerPublishConfirmation {

    private static final String NAME_EXCHANGE = "topicExchange";

    private static final String ROUTING_KEY_TOPIC = "quick.orange.rabbit";

    private static final String URI_AMQP = "${URI_AMQP}";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUri(URI_AMQP);

        try(Connection connection = factory.newConnection()) {

            Channel channel = connection.createChannel();

            AMQP.Confirm.SelectOk selectOk = channel.confirmSelect();

            channel.exchangeDeclare(NAME_EXCHANGE, BuiltinExchangeType.TOPIC);

            String message =  "This is my ";
            int setOfMessages = 10;
            int outMessages = 0;


            for (int i = 0; i < setOfMessages; i++) {
                String bodyMessage = message + i;
                channel.basicPublish(NAME_EXCHANGE, ROUTING_KEY_TOPIC, null, bodyMessage.getBytes());
                log.info("[*] Sending the message: {}", bodyMessage);

                outMessages++;

                if (setOfMessages == outMessages) {
                    channel.waitForConfirmsOrDie(5000);
                    log.info("* Message confirmed");
                    outMessages = 0;
                }
            }

            if (outMessages != 0) {
                channel.waitForConfirmsOrDie(5000);
                log.info("* Second Message confirmed");
            }

            log.info("Done");
        }

    }
}
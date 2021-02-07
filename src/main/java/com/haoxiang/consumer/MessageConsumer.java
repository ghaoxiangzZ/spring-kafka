package com.haoxiang.consumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.haoxiang.common.message.ClusterMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    private static Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
    private Gson gson = new GsonBuilder().create();

    @KafkaListener(topics = "test-topic")
    public void onMessage(String payMessage) {
        ClusterMessage msg = gson.fromJson(payMessage, ClusterMessage.class);
        logger.info("MessageConsumer: onMessage: message is: [" + msg + "]");
    }
}
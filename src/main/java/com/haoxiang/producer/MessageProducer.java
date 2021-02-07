package com.haoxiang.producer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.haoxiang.common.message.ClusterMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class MessageProducer {

    private static Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    public void send(ClusterMessage clusterMessage) {
        String msg = gson.toJson(clusterMessage);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("test-topic", msg);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("消息[{}]发送成功 offset=[{}] partition=[{}]",
                        msg, result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.warn("消息[{}]发送失败{}", msg, ex.getMessage());
            }
        });
    }
}
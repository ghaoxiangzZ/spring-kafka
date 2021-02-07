package com.haoxiang.common.controller;

import com.haoxiang.common.message.ClusterMessage;
import com.haoxiang.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author haoxiang_guo
 * @version 1.0.0
 * @ClassName SpringKafkaController.java
 * @Description TODO
 * @createTime 2021年02月07日 21:52:00
 */
@Controller
public class SpringKafkaController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private MessageProducer producer;

    @GetMapping("/send")
    @ResponseBody
    public void send() {
        ClusterMessage message = new ClusterMessage();
        message.setPort(port);
        message.setSendTime(System.currentTimeMillis());
        producer.send(message);
    }
}

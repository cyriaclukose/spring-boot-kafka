package com.learn.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {


    @KafkaListener(topics = "myNewTopic", id = "myGroup")
    public void consumeMessage(String message) {
        System.out.println(message);
    }

}

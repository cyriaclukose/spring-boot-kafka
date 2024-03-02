package com.learn.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String,String> myKafkaTemplate;


    KafkaProducer (KafkaTemplate<String,String> kafkaTemplate){
        this.myKafkaTemplate=kafkaTemplate;

    }

    public void sendMessage(String message){

        myKafkaTemplate.send("myNewTopic",message);


    }

}

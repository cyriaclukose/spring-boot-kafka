package com.learn.kafka.rest;

import com.learn.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@Slf4j

public class DataController {


    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody String message)
    {

        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("message queued sucessfully");
    }
}

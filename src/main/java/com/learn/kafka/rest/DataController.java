package com.learn.kafka.rest;

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
    private KafkaTemplate<String,String> kafkaTemplate;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody String message)
    {

        kafkaTemplate.send("myNewTopic",message);

        return ResponseEntity.ok("message queued sucessfully");
    }
}

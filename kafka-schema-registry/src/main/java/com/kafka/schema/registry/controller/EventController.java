package com.kafka.schema.registry.controller;

import com.kafka.schema.registry.dto.Employee;
import com.kafka.schema.registry.producer.KafkaAvroProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    private KafkaAvroProducer producer;

    @PostMapping("/events")
    public String sendMessage(@RequestBody Employee employee){

        producer.send(employee);
        return "message sent";


    }
}

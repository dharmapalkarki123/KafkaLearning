package com.learningkafka.controller;

import com.learningkafka.dto.Customer;
import com.learningkafka.service.KafkaMessagePublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    private KafkaMessagePublisher publisher;
    public EventController(KafkaMessagePublisher publisher) {
        this.publisher = publisher;
    }

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message) {

        try{

            for(int i=0; i<=100000; i++){
                publisher.sendMessageToTopic(message+ " : " + i);
            }


            return ResponseEntity.ok("message published successfully ...");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }



    @PostMapping("/publish1")
    public void sendEvent(@RequestBody Customer customer) {
        publisher.sendEventToTopic(customer);
    }




}

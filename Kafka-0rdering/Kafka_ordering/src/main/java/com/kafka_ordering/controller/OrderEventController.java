package com.kafka_ordering.controller;

import com.kafka_ordering.publisher.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order-events")
@RestController
public class OrderEventController {


    private final OrderProducer producer;

    public OrderEventController(OrderProducer producer) {
        this.producer = producer;
    }


    @PostMapping("/process")
    public void processOrder(@RequestParam String orderId){
        producer.processOrder(orderId);
    }

    @PostMapping("/processWithKey")
    public void processOrderWithKey(@RequestParam String orderId){
        producer.processOrderWithPartitionKey(orderId);
    }




}

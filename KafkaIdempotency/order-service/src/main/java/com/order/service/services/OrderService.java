package com.order.service.services;

import com.order.service.dto.OrderDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private  KafkaTemplate<String, Object> kafkaTemplate;

    public OrderService(KafkaTemplate<String,Object> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public String placeOrder(OrderDto orderDto) {
        // Logic to save order in database

        // Publish order event to Kafka
        orderDto.setRequestId(java.util.UUID.randomUUID().toString());
        kafkaTemplate
                .send("ORDER_TOPIC",orderDto.getOrderId(), orderDto);

        return "Order placed successfully!";
    }
}

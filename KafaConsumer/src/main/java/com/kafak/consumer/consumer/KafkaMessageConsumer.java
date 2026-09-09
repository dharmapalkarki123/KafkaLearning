package com.kafak.consumer.consumer;
import com.kafak.consumer.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;



@Service
public class KafkaMessageConsumer {
    Logger log= LoggerFactory.getLogger(KafkaMessageConsumer.class);

    @KafkaListener(topics="kafkalearning7", groupId = "jt-group-6")
    public void consumeEvents(Customer customer) {

        log.info("consume1 consume the events {} ", customer.toString());

    }


//    @KafkaListener(topics="kafkalearning4", groupId = "jt-group-3")
//    public void consumer2(String message){
//
//        log.info("consumer2 consume messages {} ", message);
//
//    }
//
//    @KafkaListener(topics="kafkalearning4", groupId = "jt-group-3")
//    public void consumer3(String message){
//
//        log.info("consumer3 consume messages {} ", message);
//
//    }




}

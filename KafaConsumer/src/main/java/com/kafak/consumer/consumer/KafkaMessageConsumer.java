package com.kafak.consumer.consumer;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

//import com.kafak.consumer.dto.Customer;









@Service
public class KafkaMessageConsumer {
    Logger log= LoggerFactory.getLogger(KafkaMessageConsumer.class);

//    @KafkaListener(topics="kafkalearning8", groupId = "jt-group-7")
//    public void consumerEvent(Customer customer) {
//
//        log.info("consume1 consume the events {} ", customer.toString());
//
//    }

//    @KafkaListener(topics="kafkalearning", groupId = "jt-group-7",
//            topicPartitions = @TopicPartition(topic = "kafkalearning", partitions = {"2"}))
//    public void consumeEvents(String customer) {
//
//        log.info("consume1 consume the events {} ", customer.toString());
//
//    }

    @KafkaListener(
            topicPartitions = @TopicPartition(
                    topic = "kafkalearning",
                    partitions = {"2"}
            ),
            groupId = "jt-group-7"
    )
    public void consumeEvents(String customer) {
        log.info("consume1 consume the events {}", customer);
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

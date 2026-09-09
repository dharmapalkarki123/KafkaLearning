package com.learningkafka.service;

import com.learningkafka.dto.Customer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    private KafkaTemplate<String,Object> template;


    public KafkaMessagePublisher(KafkaTemplate<String,Object> template) {
        this.template = template;
    }




    public void sendMessageToTopic(String message) {
        CompletableFuture<SendResult<String, Object>> future = template.send("kafkalearning7", message);
        future.whenComplete((result, ex) -> {

            if (ex == null) {

                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");


            } else {

                System.out.println("Unable to send message=[" +
                        message + "] due to: " + ex.getMessage()
                );


            }

        });


    }

    public void sendEventToTopic(Customer customer) {

        try{
            CompletableFuture<SendResult<String, Object>> future = template.send("kafkalearning7", customer);
            future.whenComplete((result, ex) -> {

                if (ex == null) {

                    System.out.println("Sent message=[" + customer.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");


                } else {

                    System.out.println("Unable to send message=[" +
                            customer.toString() + "] due to: " + ex.getMessage()
                    );


                }

            });

        }
        catch(Exception ex){
            System.out.println("Error:" +ex.getMessage());
        }




    }

}

//package com.kafka_ordering.consumer;
//
//import com.kafka_ordering.event.OrderEvent;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@Service
//public class OrderEventConsumerParallel {
//
//
//    private final ExecutorService executor = Executors
//            .newFixedThreadPool(4);
//
//
//    @KafkaListener(topics = "order-events",
//            groupId = "order-group"
//            properties = {
//                    "max.poll.records=500"
//            }
//    )
//    public void consumeBatch(List<ConsumerRecord<String, OrderEvent>> records) {
//        Map<Integer, ExecutorService> partitionExecutors = new ConcurrentHashMap<>();
//
//        for (ConsumerRecord<String, OrderEvent> record : records) {
//
//            partitionExecutors
//                    .computeIfAbsent(record.partition(), p -> Executors.newSingleThreadExecutor())
//                    .submit(() -> {
//                        OrderEvent event = record.value();
//
//                        System.out.printf("CONSUMER (parallel) -> partition=%d offset=%d orderId=%s seq=%d event=%s%n",
//                                record.partition(),
//                                record.offset(),
//                                event.orderId(),
//                                event.seq(),
//                                event.eventType());
//                    });
//        }
//    }
//
//
//}


package com.kafka_ordering.consumer;

import com.kafka_ordering.event.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class OrderEventConsumerParallel {

    // fixed thread pool (good)
    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    @KafkaListener(
            topics = "order-events",
            groupId = "order-group-parallel"
    )
    public void consumeBatch(List<OrderEvent> events) {

        Map<Integer, ExecutorService> partitionExecutors = new ConcurrentHashMap<>();

        for (OrderEvent event : events) {

            executor.submit(() -> {
                System.out.printf(
                        "CONSUMER (parallel) -> orderId=%s seq=%d event=%s%n",
                        event.orderId(),
                        event.seq(),
                        event.eventType()
                );
            });
        }
    }
}
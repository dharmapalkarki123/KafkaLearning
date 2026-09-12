package com.learningkafka;

import com.learningkafka.dto.Customer;
import com.learningkafka.service.KafkaMessagePublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class LearningKafkaApplicationTests {

    @Autowired
    private    KafkaMessagePublisher kafkaMessagePublisher;




    @Container
   static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.5.0"));


    @DynamicPropertySource
            public static void initKafkaProperties(DynamicPropertyRegistry registry) {

        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);

            }

    @Test
    public void testSendEventsToTopic() {
        kafkaMessagePublisher.sendEventToTopic(new Customer(222,"test user","test@gmail.com","98765"));

        await().pollInterval(Duration.ofSeconds(3)).atMost(10, TimeUnit.SECONDS).untilAsserted(()->{

        });
    }

}

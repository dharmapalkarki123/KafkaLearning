package com.kafak.consumer;

import org.junit.jupiter.api.Test ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.kafak.consumer.dto.Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@Slf4j
class KafaConsumerApplicationTests {
	
	
	
	  @Container
	   static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.5.0"));


	    @DynamicPropertySource
	            public static void initKafkaProperties(DynamicPropertyRegistry registry) {

	        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);

	            }

	    @Autowired
	    private KafkaTemplate<String, Object> kafkaTemplate;
	    
	    @Test
	    public void testConsumeEvents() {
	    	log.info("testConsumer method executed started:");
	    	Customer customer=new Customer(222,"test user","test@gmail.com","98765");
	    	kafkaTemplate.send("kafkalearning7", customer);
	    	log.info("testConsumerEvent executed ended");
	    	
	    }

}

//package com.kafak.consumer;
//
//import org.junit.jupiter.api.Test ;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.testcontainers.containers.KafkaContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.utility.DockerImageName;
//
//import com.kafak.consumer.dto.Customer;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//
//import lombok.extern.slf4j.Slf4j;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Testcontainers
//@Slf4j
//class KafaConsumerApplicationTests {
//
//
//
//	  @Container
//	   static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.5.0"));
//
//
//	    @DynamicPropertySource
//	            public static void initKafkaProperties(DynamicPropertyRegistry registry) {
//
//	        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
//
//	            }
//
//	    @Autowired
//	    private KafkaTemplate<String, Object> kafkaTemplate;
//
//	    @Test
//	    public void testConsumeEvents() {
//	    	log.info("testConsumer method executed started:");
//	    	Customer customer=new Customer(222,"test user","test@gmail.com","98765");
//	    	kafkaTemplate.send("kafkalearning7", customer);
//	    	log.info("testConsumerEvent executed ended");
//
//	    }
//
//}



package com.kafak.consumer;

import com.kafak.consumer.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@Slf4j
class KafaConsumerApplicationTests {

	@Container
	static KafkaContainer kafka =
			new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.5.0"));

	@DynamicPropertySource
	static void initKafkaProperties(DynamicPropertyRegistry registry) {

		registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);

		//  PRODUCER FIX (MOST IMPORTANT)
		registry.add("spring.kafka.producer.key-serializer",
				() -> "org.apache.kafka.common.serialization.StringSerializer");

		registry.add("spring.kafka.producer.value-serializer",
				() -> "org.springframework.kafka.support.serializer.JsonSerializer");

		// CONSUMER FIX
		registry.add("spring.kafka.consumer.value-deserializer",
				() -> "org.springframework.kafka.support.serializer.JsonDeserializer");

		registry.add("spring.kafka.consumer.properties.spring.json.trusted.packages",
				() -> "*");

		registry.add("spring.kafka.consumer.properties.spring.json.use.type.headers",
				() -> "false");

		registry.add("spring.kafka.consumer.properties.spring.json.value.default.type",
				() -> "com.kafak.consumer.dto.Customer");
	}

	//  FIXED TYPE (IMPORTANT)
	@Autowired
	private KafkaTemplate<String,Object> kafkaTemplate;

	@Test
	void testConsumeEvents() {

		log.info("testConsumer method started");

		Customer customer = new Customer(
				222,
				"test user",
				"test@gmail.com",
				"98765"
		);

		kafkaTemplate.send("kafkalearning7", customer);

		log.info("testConsumer method ended");
	}
}

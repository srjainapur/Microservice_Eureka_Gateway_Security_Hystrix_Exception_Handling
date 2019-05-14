package com.java.order.kafka.listner;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	
	@KafkaListener(topics="microservicetopic", groupId="group_id", containerFactory="kafkaListenerContainerFactory")
	public void consumeStringMsg(String message) {
		System.out.println("Message from Kafka Producer !!!!!!!!!!!!!!!!!");
		System.out.println(message);
	}
}

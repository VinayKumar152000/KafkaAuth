package com.example.demo.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.messaging.Message;

import com.example.demo.dto.UserEvent;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserProducer {

	private NewTopic topic;
	private KafkaTemplate<String, UserEvent> kafkaTemplate;

	public void sendMessage(UserEvent event) {
		Message<UserEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC, topic.name())
				.build();
		kafkaTemplate.send(message);
	}

}

package com.example.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserEvent;
import com.example.demo.services.EmailService;

@Service

public class UserConsumer {

	@Autowired
	EmailService service;

	@KafkaListener(topics = "${spring.kafka.topic.name}")
	public void consume(UserEvent event) {

		// email sending
		try {
			service.sendEmail(event);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

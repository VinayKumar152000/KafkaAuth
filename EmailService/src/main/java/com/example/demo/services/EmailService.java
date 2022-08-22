package com.example.demo.services;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserEvent;

import freemarker.template.Configuration;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Configuration configuration;

	public void sendEmail(UserEvent event) throws Exception {

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		helper.setSubject("Registration Successfull");
		helper.setTo(event.getEmail());

		String emailContent = getEmailContent(event);
		helper.setText(emailContent, true);
		mailSender.send(mimeMessage);
		System.out.println("Mailsend successfully");
	}

	String getEmailContent(UserEvent user) throws Exception {
		StringWriter stringWriter = new StringWriter();
		Map<String, Object> model = new HashMap<>();
		model.put("user", user);
		configuration.getTemplate("EmailTemplate.ftlh").process(model, stringWriter);
		return stringWriter.getBuffer().toString();
	}

}

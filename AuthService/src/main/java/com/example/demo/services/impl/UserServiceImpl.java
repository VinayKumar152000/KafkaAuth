package com.example.demo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bo.UserBo;
import com.example.demo.domain.User;
import com.example.demo.dto.UserEvent;
import com.example.demo.kafka.UserProducer;
import com.example.demo.payload.UserPayload;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private UserProducer userProducer;

	@Autowired
	private ModelMapper modelMapper;

	public UserBo createUser(UserPayload user) {

		UserEvent event = new UserEvent();
		String name = user.getName();
		String email = user.getEmail();

		User u = new User();
		u.setName(name);
		u.setEmail(email);

		int num = (int) (Math.random() * 1000000);
		String password = num + "";

		event.setEmail(email);
		event.setPassword(password);
		u.setPassword(password);

		// email service get run when consumer consumes this event
		userProducer.sendMessage(event);
		this.repo.save(u);

		// user to userbo
		User users = this.repo.findByEmail(email);
		UserBo userbo = UsertoUserBo(u);
		return userbo;
	}

	public UserBo UsertoUserBo(User user) {
		UserBo productbo = this.modelMapper.map(user, UserBo.class);
		return productbo;
	}
}

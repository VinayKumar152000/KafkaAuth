package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bo.UserBo;
import com.example.demo.payload.UserPayload;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping
	public UserBo createUser(@RequestBody UserPayload user) throws Exception {
		return service.createUser(user);
	}
	
}

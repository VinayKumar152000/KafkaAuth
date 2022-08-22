package com.example.demo.services;

import com.example.demo.bo.UserBo;
import com.example.demo.payload.UserPayload;

public interface UserService {

	public UserBo createUser(UserPayload user);

}

package com.microservice.user.service.services;

import java.util.List;

import com.microservice.user.service.entities.UserEntity;

public interface UserService {
	
	UserEntity addUser(UserEntity user);
	
	List<UserEntity> getAllUser();
	
	UserEntity getUser(String userId) throws Exception;

}

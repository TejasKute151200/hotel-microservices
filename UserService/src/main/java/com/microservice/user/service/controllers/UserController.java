package com.microservice.user.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.user.service.entities.UserEntity;
import com.microservice.user.service.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
		UserEntity entity = this.userService.addUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(entity);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserEntity> getUser(@PathVariable String userId) throws Exception {
		
		UserEntity user = this.userService.getUser(userId);
		return ResponseEntity.ok(user);	
	}
	
	@GetMapping()
	public ResponseEntity<List<UserEntity>> getAllUser() {
		
		List<UserEntity> list = this.userService.getAllUser();
		return ResponseEntity.ok(list);
	}

}

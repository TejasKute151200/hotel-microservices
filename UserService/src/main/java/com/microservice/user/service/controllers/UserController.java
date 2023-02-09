package com.microservice.user.service.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.microservice.user.service.services.impl.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/user")
public class UserController {

	private Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
		UserEntity entity = this.userService.addUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(entity);
	}

	int retryCount = 1;

	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//	@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "userRateLimiter" , fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<UserEntity> getUser(@PathVariable String userId) throws Exception {

		LOG.info("Get Single User Handler: UserController");

		LOG.info("Retry Count: {}", retryCount);
		retryCount++;

		UserEntity user = this.userService.getUser(userId);

		return ResponseEntity.ok(user);
	}

//	creating fall back method for circuit breaker
	public ResponseEntity<UserEntity> ratingHotelFallback(String userId, Exception e) {

//		LOG.info("Fall Back is Executed Because Service is Down : ", e.getMessage());
		
		UserEntity user = UserEntity.builder().email("dummy@gmail.com").name("dummy")
				.about("this user is dummy because some service down").userId("141234").build();
		return new ResponseEntity<UserEntity>(user, HttpStatus.OK);

	}

	@GetMapping()
	public ResponseEntity<List<UserEntity>> getAllUser() {

		List<UserEntity> list = this.userService.getAllUser();
		return ResponseEntity.ok(list);
	}

}

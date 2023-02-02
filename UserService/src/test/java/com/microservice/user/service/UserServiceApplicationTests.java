package com.microservice.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.microservice.user.service.entities.RatingEntity;
import com.microservice.user.service.external.services.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

//	@Test
//	void createRating() {
//
//		RatingEntity rating = RatingEntity.builder().rating(10).userId("").hotelId("")
//				.feedback("this is created using feign client").build();
//
//		ResponseEntity<RatingEntity> ratingResponse = ratingService.createRating(rating);
//		ratingResponse.getStatusCode();
//		ratingResponse.getBody();
//		
//		System.out.println("new rating created");
//
//	}

}

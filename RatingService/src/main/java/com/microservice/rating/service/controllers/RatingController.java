package com.microservice.rating.service.controllers;

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

import com.microservice.rating.service.entities.RatingEntity;
import com.microservice.rating.service.services.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@PostMapping()
	public ResponseEntity<RatingEntity> createRating(@RequestBody RatingEntity rating) {
		RatingEntity entity = this.ratingService.add(rating);
		return ResponseEntity.status(HttpStatus.CREATED).body(entity);
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<List<RatingEntity>> getRatingByRatingId(@PathVariable String userId) throws Exception {

		List<RatingEntity> rating = this.ratingService.getRatingByUserId(userId);
		return ResponseEntity.ok(rating);
	}

	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<RatingEntity>> getRatingByHotelId(@PathVariable String hotelId) throws Exception {

		List<RatingEntity> rating = this.ratingService.getRatingByHotelId(hotelId);
		return ResponseEntity.ok(rating);
	}

	@GetMapping()
	public ResponseEntity<List<RatingEntity>> getAllRating() {

		List<RatingEntity> list = this.ratingService.getAllRating();
		return ResponseEntity.ok(list);
	}

}

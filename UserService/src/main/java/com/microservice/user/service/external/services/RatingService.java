package com.microservice.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.microservice.user.service.entities.RatingEntity;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
	
	@PostMapping("/rating")
//	@PostMapping("/api/rating")
	public ResponseEntity<RatingEntity> createRating(RatingEntity values);
	
//	@GetMapping("/api/rating/{ratingId}")
//	public RatingEntity getRating(@PathVariable("ratingId") String ratingId);
	
	@PutMapping("/rating/{ratingId}")
//	@PutMapping("/api/rating/{ratingId}")
	public ResponseEntity<RatingEntity> updateRating(@PathVariable("ratingId") String ratingId, RatingEntity rating);
	
	@DeleteMapping("/rating/{ratingId}")
//	@DeleteMapping("/api/rating/{ratingId}")
	public ResponseEntity<RatingEntity> deleteRating(@PathVariable String ratingId);

}

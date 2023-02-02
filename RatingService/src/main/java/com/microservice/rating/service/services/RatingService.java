package com.microservice.rating.service.services;

import java.util.List;

import com.microservice.rating.service.entities.RatingEntity;

public interface RatingService {
	
	public RatingEntity add(RatingEntity rating);
	
	public List<RatingEntity> getAllRating();
	
	public List<RatingEntity> getRatingByUserId(String userId);
	
	public List<RatingEntity> getRatingByHotelId(String hotelId);

}

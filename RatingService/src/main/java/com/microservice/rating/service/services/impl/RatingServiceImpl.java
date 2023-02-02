package com.microservice.rating.service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.rating.service.entities.RatingEntity;
import com.microservice.rating.service.repo.RatingRepo;
import com.microservice.rating.service.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepo ratingRepo;

	@Override
	public RatingEntity add(RatingEntity rating) {

		return this.ratingRepo.save(rating);
	}

	@Override
	public List<RatingEntity> getAllRating() {

		return this.ratingRepo.findAll();
	}

	@Override
	public List<RatingEntity> getRatingByUserId(String userId) {

		return this.ratingRepo.findByUserId(userId);
	}

	@Override
	public List<RatingEntity> getRatingByHotelId(String hotelId) {

		return this.ratingRepo.findByHotelId(hotelId);
	}

}

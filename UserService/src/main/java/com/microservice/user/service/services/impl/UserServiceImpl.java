package com.microservice.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.user.service.entities.HotelEntity;
import com.microservice.user.service.entities.RatingEntity;
import com.microservice.user.service.entities.UserEntity;
import com.microservice.user.service.exceptions.ResourceNotFoundException;
import com.microservice.user.service.external.services.HotelService;
import com.microservice.user.service.repo.UserRepo;
import com.microservice.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;

	private Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public UserEntity addUser(UserEntity user) {

		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);

//		UserEntity entity = new UserEntity();
//		entity.setUserId(user.getUserId());
//		entity.setName(user.getName());
//		entity.setEmail(user.getEmail());
//		entity.setAbout(user.getAbout());

		return this.userRepo.save(user);
	}

	@Override
	public List<UserEntity> getAllUser() {

		return this.userRepo.findAll();
	}

	@Override
	public UserEntity getUser(String userId) throws ResourceNotFoundException {

		UserEntity entity = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with id" + userId));

		/*
		 * We Cannot Direct Covert To Array List.. If We Direct Implement Below Code
		 * Then We Will Got ClassCastException LinkedHashMap
		 * 
		 * ArrayList<RatingEntity> ratingOfUser = restTemplate
		 * .getForObject("http://localhost:8083/api/rating/users/" + entity.getUserId(),
		 * ArrayList.class);
		 */

		RatingEntity[] ratingOfUser = restTemplate
				.getForObject("http://RATING-SERVICE/rating/users/" + entity.getUserId(), RatingEntity[].class);
		
		List<RatingEntity> ratings = Arrays.stream(ratingOfUser).toList();

		LOG.info("{} ", ratingOfUser);

		List<RatingEntity> ratingList = ratings.stream().map(rating -> {

			System.out.println(rating.getHotelId());

//			ResponseEntity<HotelEntity> forEntity = restTemplate
//					.getForEntity("http://HOTEL-SERVICE/api/hotel/" + rating.getHotelId(), HotelEntity.class);
//			HotelEntity hotel = forEntity.getBody();
//			LOG.info("response status code: {} ", forEntity.getStatusCode());
			
			HotelEntity hotel = hotelService.getHotel(rating.getHotelId());
			
			rating.setHotel(hotel);
			
			return rating;
			
		}).collect(Collectors.toList());
		
		entity.setRatings(ratingList);
		
		return entity;
	}

}

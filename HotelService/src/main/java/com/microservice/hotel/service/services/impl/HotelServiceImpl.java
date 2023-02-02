package com.microservice.hotel.service.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.hotel.service.entities.HotelEntity;
import com.microservice.hotel.service.exceptions.ResourceNotFoundException;
import com.microservice.hotel.service.repo.HotelRepo;
import com.microservice.hotel.service.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepo hotelRepo;

	@Override
	public HotelEntity addHotel(HotelEntity hotel) {

		String hotelId = UUID.randomUUID().toString();
		hotel.setId(hotelId);

		return this.hotelRepo.save(hotel);
	}

	@Override
	public List<HotelEntity> getAllHotel() {

		return this.hotelRepo.findAll();
	}

	@Override
	public HotelEntity getHotel(String id) {

		return this.hotelRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("hotel with given id " + id + " not found !!"));
	}

}

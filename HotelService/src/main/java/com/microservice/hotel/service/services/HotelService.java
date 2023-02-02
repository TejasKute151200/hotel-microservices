package com.microservice.hotel.service.services;

import java.util.List;

import com.microservice.hotel.service.entities.HotelEntity;

public interface HotelService {

	HotelEntity addHotel(HotelEntity hotel);

	List<HotelEntity> getAllHotel();

	HotelEntity getHotel(String id);

}

package com.microservice.user.service.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.user.service.entities.HotelEntity;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
	
	@GetMapping("/hotel")
//	@GetMapping("/api/hotel")
	public List<HotelEntity> getAllHotel();

	@GetMapping("/hotel/{hotelId}")
//	@GetMapping("/api/hotel/{hotelId}")
	public HotelEntity getHotel(@PathVariable("hotelId") String hotelId);

}

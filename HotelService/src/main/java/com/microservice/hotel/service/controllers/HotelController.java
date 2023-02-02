package com.microservice.hotel.service.controllers;

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

import com.microservice.hotel.service.entities.HotelEntity;
import com.microservice.hotel.service.services.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@PostMapping
	public ResponseEntity<HotelEntity> createHotel(@RequestBody HotelEntity hotel) {
		HotelEntity entity = this.hotelService.addHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(entity);
	}

	@GetMapping("/{hotelId}")
	public ResponseEntity<HotelEntity> getHotel(@PathVariable String hotelId) throws Exception {

		HotelEntity hotel = this.hotelService.getHotel(hotelId);
		return ResponseEntity.ok(hotel);
	}

	@GetMapping()
	public ResponseEntity<List<HotelEntity>> getAllHotel() {

		List<HotelEntity> list = this.hotelService.getAllHotel();
		return ResponseEntity.ok(list);
	}

}
package com.microservice.hotel.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.hotel.service.entities.HotelEntity;

@Repository
public interface HotelRepo extends JpaRepository<HotelEntity, String> {

}

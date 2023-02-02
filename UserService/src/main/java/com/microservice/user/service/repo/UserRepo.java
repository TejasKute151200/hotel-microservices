package com.microservice.user.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.user.service.entities.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String> {

}

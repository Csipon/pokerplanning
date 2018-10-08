package com.csipon.planningpocker.repository;

import com.csipon.planningpocker.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByToken(String token);
}

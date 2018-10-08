package com.csipon.planningpocker.repository;

import com.csipon.planningpocker.domain.PokerSession;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PokerSessionRepository extends MongoRepository<PokerSession, String> {
    Optional<PokerSession> findByRoomNumber(Integer roomNumber);
}

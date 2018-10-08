package com.csipon.planningpocker.service;

import com.csipon.planningpocker.domain.PokerSession;
import com.csipon.planningpocker.domain.User;
import com.csipon.planningpocker.exception.EntityIsNotExistException;
import com.csipon.planningpocker.exception.UserAlreadyExistException;
import com.csipon.planningpocker.repository.PokerSessionRepository;
import com.csipon.planningpocker.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class PokerSessionServiceImpl implements PokerSessionService {
    private final PokerSessionRepository pokerSessionRepository;
    private final IdGenerator idGenerator;


    public PokerSession createPokerSession() {
        PokerSession pokerSession =
                PokerSession
                        .builder()
                        .roomNumber(idGenerator.generateID())
                        .users(new HashSet<>())
                        .points(new HashMap<>())
                        .build();
        pokerSessionRepository.save(pokerSession);

        return pokerSession;
    }
    
    public PokerSession findSession(Integer roomNumber){
        return pokerSessionRepository
                .findByRoomNumber(roomNumber)
                .orElseThrow(() -> new EntityIsNotExistException("Session with room number = [" + roomNumber + "] isn't exist"));
    }


    public PokerSession addUser(Integer roomNumber, User user) {
        PokerSession pokerSession = this.findSession(roomNumber);
        boolean added = pokerSession.getUsers().add(user);
        if (!added) {
            throw new UserAlreadyExistException("User with following name [" + user.getName() + "] already in this room ");
        }
        pokerSessionRepository.save(pokerSession);
        return pokerSession;
    }


}

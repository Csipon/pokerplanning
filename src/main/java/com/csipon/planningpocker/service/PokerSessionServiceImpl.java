package com.csipon.planningpocker.service;

import com.csipon.planningpocker.domain.PokerSession;
import com.csipon.planningpocker.domain.User;
import com.csipon.planningpocker.domain.UserStory;
import com.csipon.planningpocker.domain.dto.CreateUserStoryDto;
import com.csipon.planningpocker.domain.dto.UserPointDto;
import com.csipon.planningpocker.exception.EntityIsNotExistException;
import com.csipon.planningpocker.exception.UserAlreadyExistException;
import com.csipon.planningpocker.repository.PokerSessionRepository;
import com.csipon.planningpocker.repository.UserRepository;
import com.csipon.planningpocker.service.interfaces.PokerSessionService;
import com.csipon.planningpocker.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.csipon.planningpocker.config.WebSocketConstants.MESSAGE_PREFIX;

@Service
@RequiredArgsConstructor
public class PokerSessionServiceImpl implements PokerSessionService {
    private final SimpMessagingTemplate messagingTemplate;
    private final PokerSessionRepository pokerSessionRepository;
    private final IdGenerator idGenerator;


    public PokerSession createPokerSession() {
        PokerSession pokerSession =
                PokerSession
                        .builder()
                        .roomNumber(idGenerator.generateID())
                        .users(new HashSet<>())
                        .userStories(new ArrayList<>())
                        .build();
        pokerSessionRepository.save(pokerSession);
        messagingTemplate.convertAndSend(MESSAGE_PREFIX + "/session", pokerSession);
        return pokerSession;
    }
    
    public PokerSession findSession(Integer roomNumber){
        return pokerSessionRepository
                .findByRoomNumber(roomNumber)
                .orElseThrow(() -> new EntityIsNotExistException("Session with room number = [" + roomNumber + "] isn't exist"));
    }


    public PokerSession addUser(Integer roomNumber, User user) {
        PokerSession pokerSession = this.findSession(roomNumber);
        if (pokerSession.getUsers().isEmpty()){
            pokerSession.setCreator(user);
        }
        boolean added = pokerSession.getUsers().add(user);

        if (!added) {
            throw new UserAlreadyExistException("User with following name [" + user.getName() + "] already in this room ");
        }
        pokerSessionRepository.save(pokerSession);
        messagingTemplate.convertAndSend(MESSAGE_PREFIX + "/session", pokerSession);
        return pokerSession;
    }

    @Override
    public PokerSession update(PokerSession pokerSession) {
        pokerSessionRepository.save(pokerSession);
        messagingTemplate.convertAndSend(MESSAGE_PREFIX + "/session", pokerSession);
        return pokerSession;
    }
}

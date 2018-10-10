package com.csipon.planningpocker.service;

import com.csipon.planningpocker.domain.User;
import com.csipon.planningpocker.domain.dto.CreateUserDto;
import com.csipon.planningpocker.exception.EntityIsNotExistException;
import com.csipon.planningpocker.repository.UserRepository;
import com.csipon.planningpocker.service.interfaces.PokerSessionService;
import com.csipon.planningpocker.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PokerSessionService pokerSessionService;

    @Override
    public User createUser(CreateUserDto createUserDto) {
        User user = User.builder()
                .name(createUserDto.getUsername())
                .token(UUID.randomUUID().toString())
                .build();

        user = userRepository.save(user);

        pokerSessionService.addUser(createUserDto.getRoomNumber(), user);
        return user;
    }


    @Override
    public User findUser(String token) {
        return userRepository.findByToken(token).orElseThrow(() -> new EntityIsNotExistException("User isn't exist"));
    }
}

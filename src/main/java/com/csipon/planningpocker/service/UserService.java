package com.csipon.planningpocker.service;

import com.csipon.planningpocker.domain.User;
import com.csipon.planningpocker.domain.dto.CreateUserDto;

public interface UserService {

    User createUser(CreateUserDto createUserDto);

    User findUser(String token);
}

package com.csipon.planningpocker.controller;

import com.csipon.planningpocker.domain.User;
import com.csipon.planningpocker.domain.dto.CreateUserDto;
import com.csipon.planningpocker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto){
        User user = userService.createUser(createUserDto);
        return ResponseEntity.ok().body(user);
    }
}

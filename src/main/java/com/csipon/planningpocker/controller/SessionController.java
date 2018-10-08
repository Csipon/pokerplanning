package com.csipon.planningpocker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/session")
public class SessionController {


    @PostMapping("/create")
    public ResponseEntity<String> session(HttpServletRequest request,@RequestParam String token) throws JsonProcessingException {
        log.debug(request.getSession().getId());
        if (token == null){
            token = UUID.randomUUID().toString();
            request.setAttribute("token", token);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        return ResponseEntity.ok(objectMapper.writeValueAsString(token));
    }
}

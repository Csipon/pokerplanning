package com.csipon.planningpocker.controller;

import com.csipon.planningpocker.domain.PokerSession;
import com.csipon.planningpocker.service.PokerSessionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Slf4j
@RestController
@MessageMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final PokerSessionService pokerSessionService;

    @PostMapping
    @SendTo("/topic/session")
    public PokerSession session() {
        return pokerSessionService.createPokerSession();
    }

    @GetMapping("/{roomNumber}")
    public ResponseEntity<PokerSession> findSession(@PathVariable Integer roomNumber){
        PokerSession pokerSession = pokerSessionService.findSession(roomNumber);
        return ResponseEntity.ok().body(pokerSession);
    }

}

package com.csipon.planningpocker.controller;

import com.csipon.planningpocker.domain.PokerSession;
import com.csipon.planningpocker.service.PokerSessionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final PokerSessionService pokerSessionService;

    @PostMapping
    public ResponseEntity<PokerSession> session() {
        PokerSession pokerSession = pokerSessionService.createPokerSession();
        return ResponseEntity.ok().body(pokerSession);
    }

    @GetMapping("/{roomNumber}")
    public ResponseEntity<PokerSession> findSession(@PathVariable Integer roomNumber){
        PokerSession pokerSession = pokerSessionService.findSession(roomNumber);
        return ResponseEntity.ok().body(pokerSession);
    }

}

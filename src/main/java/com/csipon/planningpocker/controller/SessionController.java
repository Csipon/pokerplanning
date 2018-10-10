package com.csipon.planningpocker.controller;

import com.csipon.planningpocker.domain.PokerSession;
import com.csipon.planningpocker.domain.dto.CreateUserStoryDto;
import com.csipon.planningpocker.service.interfaces.PokerSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final PokerSessionService pokerSessionService;

    @PostMapping
    public PokerSession session() {
        return pokerSessionService.createPokerSession();
    }

    @GetMapping("/{roomNumber}")
    public ResponseEntity<PokerSession> findSession(@PathVariable Integer roomNumber){
        PokerSession pokerSession = pokerSessionService.findSession(roomNumber);
        return ResponseEntity.ok().body(pokerSession);
    }
}

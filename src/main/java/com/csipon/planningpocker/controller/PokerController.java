package com.csipon.planningpocker.controller;

import com.csipon.planningpocker.domain.PokerSession;
import com.csipon.planningpocker.service.PokerSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PokerController {
    private final PokerSessionService pokerSessionService;

    @MessageMapping("/updateSession")
    @SendTo("/topic/session")
    public PokerSession updateSession(@Payload PokerSession pokerSession) {
        return pokerSessionService.update(pokerSession);
    }
}

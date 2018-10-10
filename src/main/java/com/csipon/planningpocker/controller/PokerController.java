package com.csipon.planningpocker.controller;

import com.csipon.planningpocker.domain.PokerSession;
import com.csipon.planningpocker.domain.dto.CreateUserStoryDto;
import com.csipon.planningpocker.domain.dto.UserPointDto;
import com.csipon.planningpocker.service.interfaces.PokerBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class PokerController {
    private final PokerBoardService pokerBoardService;


    @PostMapping("/story")
    public ResponseEntity<PokerSession> createStory(@RequestBody CreateUserStoryDto createUserStoryDto) {
        System.out.println(createUserStoryDto.getRoomNumber());
        System.out.println(createUserStoryDto.getStoryName());
        PokerSession pokerSession = pokerBoardService.createUserStory(createUserStoryDto);
        return ResponseEntity.ok(pokerSession);
    }

    @PostMapping("/point")
    public ResponseEntity<PokerSession> setPoint(@RequestBody UserPointDto userPointDto) {
        PokerSession pokerSession = pokerBoardService.setUserPoint(userPointDto);
        return ResponseEntity.ok(pokerSession);
    }
}

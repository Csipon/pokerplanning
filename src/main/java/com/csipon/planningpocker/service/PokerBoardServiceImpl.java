package com.csipon.planningpocker.service;

import com.csipon.planningpocker.domain.Point;
import com.csipon.planningpocker.domain.PokerSession;
import com.csipon.planningpocker.domain.User;
import com.csipon.planningpocker.domain.UserStory;
import com.csipon.planningpocker.domain.dto.CreateUserStoryDto;
import com.csipon.planningpocker.domain.dto.UserPointDto;
import com.csipon.planningpocker.service.interfaces.PokerBoardService;
import com.csipon.planningpocker.service.interfaces.PokerSessionService;
import com.csipon.planningpocker.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PokerBoardServiceImpl implements PokerBoardService {
    private final PokerSessionService pokerSessionService;
    private final UserService userService;

    @Override
    public PokerSession createUserStory(CreateUserStoryDto userStoryDto) {
        UserStory userStory = UserStory.builder()
                .name(userStoryDto.getStoryName())
                .points(new HashMap<>())
                .start(LocalDateTime.now())
                .build();
        PokerSession session = pokerSessionService.findSession(userStoryDto.getRoomNumber());
        if (Objects.nonNull(session.getCurrentStory())) {
            session.getUserStories().add(session.getCurrentStory());
        }
        session.setCurrentStory(userStory);
        pokerSessionService.update(session);

        return session;
    }

    @Override
    public PokerSession setUserPoint(UserPointDto userPointDto) {
        PokerSession pokerSession = pokerSessionService.findSession(userPointDto.getRoomNumber());
        User user = userService.findUser(userPointDto.getToken());
        Point point = Point.builder()
                .finish(LocalDateTime.now())
                .point(userPointDto.getPoint())
                .build();
        System.out.println(point);
        pokerSession.getCurrentStory().getPoints().put(user.getToken(), point);

        pokerSessionService.update(pokerSession);

        return pokerSession;
    }
}

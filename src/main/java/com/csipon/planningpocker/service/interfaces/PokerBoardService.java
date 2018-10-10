package com.csipon.planningpocker.service.interfaces;

import com.csipon.planningpocker.domain.PokerSession;
import com.csipon.planningpocker.domain.dto.CreateUserStoryDto;
import com.csipon.planningpocker.domain.dto.UserPointDto;

public interface PokerBoardService {
    PokerSession createUserStory(CreateUserStoryDto userStoryDto);

    PokerSession setUserPoint(UserPointDto userPointDto);

}

package com.csipon.planningpocker.service;

import com.csipon.planningpocker.domain.PokerSession;
import com.csipon.planningpocker.domain.User;

public interface PokerSessionService {
    PokerSession createPokerSession();

    PokerSession findSession(Integer roomNumber);

    PokerSession addUser(Integer roomNumber, User user);
}

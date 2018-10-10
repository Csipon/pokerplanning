package com.csipon.planningpocker.handler;

import com.csipon.planningpocker.domain.PokerSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import static com.csipon.planningpocker.config.WebSocketConstants.MESSAGE_PREFIX;

@Component
@RepositoryEventHandler(PokerSession.class)
@RequiredArgsConstructor
@Slf4j
public class PokerBoardHandler {

    private final SimpMessagingTemplate websocket;
    private final EntityLinks entityLinks;

    @HandleAfterCreate
    public void newPokerSession(PokerSession pokerSession) {
        log.info("newPokerSession [{}]", pokerSession);
        System.out.println("newPokerSession");
        this.websocket.convertAndSend(MESSAGE_PREFIX + "/session", getPath(pokerSession));
    }

    @HandleAfterSave
    public void savePokerSession(PokerSession pokerSession) {
        log.info("savePokerSession [{}]", pokerSession);
        System.out.println("savePokerSession");
        this.websocket.convertAndSend(MESSAGE_PREFIX + "/session", getPath(pokerSession));
    }

    private String getPath(PokerSession pokerSession) {
        System.out.println("getPath");
        return this.entityLinks.linkForSingleResource(pokerSession.getClass(), pokerSession.getId()).toUri().getPath();
    }
}

package com.csipon.planningpocker.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@Document(collection = "pokerSession")
public class PokerSession {
    @Id
    private String id;
    private Integer roomNumber;
    private User creator;
    private Set<User> users;
    private UserStory currentStory;
    private List<UserStory> userStories;
}

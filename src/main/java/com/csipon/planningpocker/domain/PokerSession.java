package com.csipon.planningpocker.domain;

import javafx.beans.DefaultProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
@Document(collection = "pokerSession")
public class PokerSession {
    @Id
    private String id;
    private Integer roomNumber;
    private Set<User> users;
    private Map<User, Integer> points;
}

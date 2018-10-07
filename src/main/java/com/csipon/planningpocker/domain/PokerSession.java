package com.csipon.planningpocker.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PokerSession {
    private Integer id;
    private List<User> users;
    private Map<User, Integer> points;
}

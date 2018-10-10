package com.csipon.planningpocker.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;


@Getter
@Setter
@Builder
public class UserStory {
    private String name;
    private Map<String, Point> points;
    private LocalDateTime start;
    private LocalDateTime end;
    private Integer finalPoint;
}

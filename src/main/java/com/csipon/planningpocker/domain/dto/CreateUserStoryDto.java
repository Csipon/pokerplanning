package com.csipon.planningpocker.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserStoryDto {
    private Integer roomNumber;
    private String storyName;
}

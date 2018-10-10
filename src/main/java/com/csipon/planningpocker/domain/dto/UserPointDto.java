package com.csipon.planningpocker.domain.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPointDto {
    private Integer roomNumber;
    private String token;
    private Integer point;
}

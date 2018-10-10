package com.csipon.planningpocker.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class Point {
    private Integer point;
    private LocalDateTime finish;
}

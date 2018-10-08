package com.csipon.planningpocker.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@EqualsAndHashCode
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String token;
}

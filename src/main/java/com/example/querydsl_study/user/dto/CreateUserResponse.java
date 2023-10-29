package com.example.querydsl_study.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateUserResponse {

    private long id;
    private String email;

    @Builder
    public CreateUserResponse(long id, String email) {
        this.id = id;
        this.email = email;
    }
}

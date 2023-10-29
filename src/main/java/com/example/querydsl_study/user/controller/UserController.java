package com.example.querydsl_study.user.controller;


import com.example.querydsl_study.user.dto.CreateUserRequest;
import com.example.querydsl_study.user.dto.CreateUserResponse;
import com.example.querydsl_study.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping("/api/v1/user")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }
}
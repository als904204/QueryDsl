package com.example.querydsl_study.user.service;

import com.example.querydsl_study.user.dto.CreateUserRequest;
import com.example.querydsl_study.user.dto.CreateUserResponse;
import com.example.querydsl_study.user.entity.User;
import com.example.querydsl_study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) {

        User user = User.builder()
            .address(request.getAddress())
            .email(request.getEmail())
            .name(request.getName())
            .build();

        userRepository.save(user);

        return CreateUserResponse.builder()
            .id(user.getId())
            .email(user.getEmail())
            .build();
    }


}

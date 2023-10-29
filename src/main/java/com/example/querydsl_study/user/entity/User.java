package com.example.querydsl_study.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tb_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // new User() X
@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @Column(unique = true)
    private String email;


    @Builder
    public User(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }


}

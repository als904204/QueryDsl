package com.example.querydsl_study.user.entity;

import com.example.querydsl_study.order.entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // new User() X
@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orderList = new ArrayList<>();


    @Builder
    public User(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }


}

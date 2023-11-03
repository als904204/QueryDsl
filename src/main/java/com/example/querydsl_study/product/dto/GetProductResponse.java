package com.example.querydsl_study.product.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetProductResponse {

    private String name;
    private Long price;
    private String category;

    @Builder
    public GetProductResponse(String name, Long price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}

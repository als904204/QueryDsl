package com.example.querydsl_study.product.service;

import com.example.querydsl_study.product.dto.GetProductResponse;
import com.example.querydsl_study.product.repository.ProductRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;


    // 1. category 가 없으면 전체 조회
    // 2. category 가 있으면 조건 조회
    public List<GetProductResponse> getProductListV1(String category) {

        List<GetProductResponse> response;

        if (Objects.isNull(category)) {
            response = productRepository.findAll().stream()
                .map(m -> GetProductResponse.builder()
                    .name(m.getName())
                    .price(m.getPrice())
                    .category(m.getCategory())
                    .build())
                .toList();
        } else {
            response = productRepository.getProductListV1(category).stream()
                .map(m -> GetProductResponse.builder()
                    .name(m.getName())
                    .price(m.getPrice())
                    .category(m.getCategory())
                    .build())
                .toList();
        }

        return response;
    }

}

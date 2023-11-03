package com.example.querydsl_study.product.service;

import com.example.querydsl_study.product.dto.GetProductResponse;
import com.example.querydsl_study.product.dto.ProductSortByCondition;
import com.example.querydsl_study.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    // V1 : 모든 상품 조회 OR 카테코리에 해당하는 모든 상품 조회 (객체 자체 조회)
    public List<GetProductResponse> getProductListV1(String category) {
        return productRepository.getProductListV1(category).stream()
            .map(m -> GetProductResponse.builder()
                .name(m.getName())
                .price(m.getPrice())
                .category(m.getCategory())
                .build())
            .toList();
    }

    // V2 : 모든 상품 조회 OR 카테코리에 해당하는 모든 상품 조회 (필요한 컬럼만 조회)
    public List<GetProductResponse> getProductListV2(String category) {
        return productRepository.getProductListV2(category);
    }

    // V2 : 카테코리 + 페이징으로 상품을 조회한다
    public List<GetProductResponse> getProductListV2WithPage(String category,int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return productRepository.getProductListV2WithPage(category,pageRequest.getOffset(),pageRequest.getPageSize()).stream()
            .map(m -> GetProductResponse.builder()
                .name(m.getName())
                .price(m.getPrice())
                .category(m.getCategory())
                .build())
            .toList();
    }

    // V3 : 카테코리 + 페이징 + 가격 내림차순 정렬로 상품을 조회한다
    public List<GetProductResponse> getProductListV3WithPageAndSortPriceDesc(String category, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        return productRepository.getProductListV3WithPageAndSortPriceDesc(category, pageRequest.getOffset(),
                pageRequest.getPageSize())
            .stream()
            .map(m ->
                GetProductResponse.builder()
                    .name(m.getName())
                    .price(m.getPrice())
                    .category(m.getCategory())
                    .build())
            .toList();
    }

    // V4 : 카테코리 + 페이징 + 상품 조건별(name,price,id) 별로 내림차순 정렬
    public List<GetProductResponse> getProductListV4WithPageAndSortByCondition(
        String category,
        ProductSortByCondition condition,
        int page,
        int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        return productRepository.getProductListV4WithPageAndSortByCondition(category,condition, pageRequest.getOffset(),
                pageRequest.getPageSize())
            .stream()
            .map(m ->
                GetProductResponse.builder()
                    .name(m.getName())
                    .price(m.getPrice())
                    .category(m.getCategory())
                    .build())
            .toList();
    }
}

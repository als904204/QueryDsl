package com.example.querydsl_study.product.service;

import com.example.querydsl_study.product.dto.GetProductResponse;
import com.example.querydsl_study.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    // V1 : 카테코리별로 상품을 조회한다.
    public List<GetProductResponse> getProductListV1(String category) {
        return productRepository.getProductListV1(category).stream()
            .map(m -> GetProductResponse.builder()
                .name(m.getName())
                .price(m.getPrice())
                .category(m.getCategory())
                .build())
            .toList();
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
    public List<GetProductResponse> getProductListWithPageAndSortPriceDesc(String category, int page, int size) {

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
}

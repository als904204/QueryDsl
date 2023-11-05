package com.example.querydsl_study.product.controller;


import com.example.querydsl_study.product.dto.GetProductResponse;
import com.example.querydsl_study.product.dto.ProductSortByCondition;
import com.example.querydsl_study.product.service.ProductService;
import io.micrometer.common.lang.Nullable;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;

    // V1 : 모든 상품 조회 또는 category 상품 조회
    @GetMapping("/api/v1/product")
    public List<GetProductResponse> getProductListV1(
        @Nullable @RequestParam("category") String category) {
        return productService.getProductListV1(category);
    }

    // V2 : 모든 상품 조회 또는 category 상품 조회
    @GetMapping("/api/v2/product")
    public List<GetProductResponse> getProductListV2(
        @Nullable @RequestParam("category") String category) {
        return productService.getProductListV2(category);
    }


    // 저장된 데이터 페이징 조회
    @GetMapping("/api/v3/product")
    public List<GetProductResponse> getProductListV3WithPage(
        @Nullable @RequestParam("category") String category,
        @RequestParam("page") int page,
        @RequestParam("size") int size
    ) {
        return productService.getProductListV3WithPage(category, page, size);
    }

    @GetMapping("/api/v4/product")
    public List<GetProductResponse> getProductListWithV5PageAndSortPriceDesc(
        @Nullable @RequestParam("category") String category,
        @RequestParam("page") int page,
        @RequestParam("size") int size) {
        // 페이징 처리 2: 가격 기준 내림차순 정렬된 데이터에 대한 페이징 처리
        return productService.getProductListV4WithPageAndSortPriceDesc(category, page, size);
    }

    @GetMapping("/api/v5/product")
    public List<GetProductResponse> getProductListWithV5PageAndSortPriceDesc(
        @Nullable @RequestParam(value = "category", required = false) String category,
        @Nullable @RequestParam(required = false) ProductSortByCondition condition,
        @RequestParam("page") int page,
        @RequestParam("size") int size) {
        return productService.getProductListV5WithPageAndSortByCondition(category, condition, page,
            size);
    }

}
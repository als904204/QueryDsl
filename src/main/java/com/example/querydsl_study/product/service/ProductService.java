package com.example.querydsl_study.product.service;

import com.example.querydsl_study.product.dto.GetProductResponse;
import com.example.querydsl_study.product.dto.ProductSortByCondition;
import com.example.querydsl_study.product.entity.Product;
import com.example.querydsl_study.product.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("상품 없음"));
    }

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

//    @PostConstruct
    public void testDats() {
        log.info("testDats()");
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            products.add(new Product("Product " + i, 1L, "Category"));
        }
        log.info("products.size={}",products.size());

        productRepository.saveAll(products);
    }


//    @PostConstruct
    public void test() {
        String category = "Category";

        log.info("getProductListV1.V1 실행");
        long startTimeV1 = System.currentTimeMillis();
        getProductListV1(category);
        long endTimeV1 = System.currentTimeMillis();
        System.out.println("V1 Method Time: " + (endTimeV1 - startTimeV1) + "ms");

        System.out.println("==============");

        log.info("getProductListV1.V2 실행");
        long startTimeV2 = System.currentTimeMillis();
        getProductListV2(category);
        long endTimeV2 = System.currentTimeMillis();
        System.out.println("V2 Method Time: " + (endTimeV2 - startTimeV2) + "ms");
    }



    // V3 : 카테코리 + 페이징으로 상품을 조회한다
    public List<GetProductResponse> getProductListV3WithPage(String category,int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return productRepository.getProductListV3WithPage(category,pageRequest.getOffset(),pageRequest.getPageSize()).stream()
            .map(m -> GetProductResponse.builder()
                .name(m.getName())
                .price(m.getPrice())
                .category(m.getCategory())
                .build())
            .toList();
    }

    // V4 : 카테코리 + 페이징 + 가격 내림차순 정렬로 상품을 조회한다
    public List<GetProductResponse> getProductListV4WithPageAndSortPriceDesc(String category, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        return productRepository.getProductListV4WithPageAndSortPriceDesc(category, pageRequest.getOffset(),
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

    // V5 : 카테코리 + 페이징 + 상품 조건별(name,price,id) 별로 내림차순 정렬
    public List<GetProductResponse> getProductListV5WithPageAndSortByCondition(
        String category,
        ProductSortByCondition condition,
        int page,
        int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        return productRepository.getProductListV5WithPageAndSortByCondition(category,condition, pageRequest.getOffset(),
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

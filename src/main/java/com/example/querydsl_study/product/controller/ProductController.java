package com.example.querydsl_study.product.controller;


import com.example.querydsl_study.product.dto.GetProductResponse;
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


    @GetMapping("/api/v1/product")
    public List<GetProductResponse> getProductList(@Nullable @RequestParam("category") String category) {

        return productService.getProductList(category);
    }

}
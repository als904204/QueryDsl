package com.example.querydsl_study.product.repository;

import com.example.querydsl_study.product.dto.GetProductResponse;
import com.example.querydsl_study.product.dto.ProductSortByCondition;
import com.example.querydsl_study.product.entity.Product;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryCustom{

    List<Product> getProductListV1(String category);
    List<GetProductResponse> getProductListV2(String category);

    List<Product> getProductListV2WithPage(String category,long offset, int pageSize);
    List<Product> getProductListV3WithPageAndSortPriceDesc(String category, long offset, int pageSize);
    List<Product> getProductListV4WithPageAndSortByCondition(String category, ProductSortByCondition condition,long offset, int pageSize);
}

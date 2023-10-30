package com.example.querydsl_study.product.repository;

import com.example.querydsl_study.product.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryCustom{

    List<Product> getProductListV1(String category);
    List<Product> getProductListWithPage(String category,long offset, int pageSize);
    List<Product> getProductListWithPageAndSortPriceDesc(String category, long offset, int pageSize);
}

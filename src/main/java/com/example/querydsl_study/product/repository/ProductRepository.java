package com.example.querydsl_study.product.repository;

import com.example.querydsl_study.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>,ProductRepositoryCustom {

}

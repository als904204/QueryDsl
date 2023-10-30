package com.example.querydsl_study.util.converter;

import com.example.querydsl_study.product.dto.ProductSortByCondition;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToProductSortConditionConverter implements Converter<String, ProductSortByCondition> {

    @Override
    public ProductSortByCondition convert(@NonNull String source) {
        ProductSortByCondition condition = new ProductSortByCondition();
        condition.setSortBy(source);
        return condition;
    }
}

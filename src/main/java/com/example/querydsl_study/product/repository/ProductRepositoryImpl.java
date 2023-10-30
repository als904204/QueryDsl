package com.example.querydsl_study.product.repository;

import static com.example.querydsl_study.product.entity.QProduct.product;

import com.example.querydsl_study.product.entity.Product;
import com.example.querydsl_study.product.entity.QProduct;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> getProductListV1(String category) {
        QProduct product = QProduct.product;

        // 현재는 product 의 모든 컬럼을 조회한다
        // TODO : 프로젝션을 이용하면 원하는 컬럼 값만 리턴 받을 수 있다
        return jpaQueryFactory.select(product)
            .from(product)
            .where(eqCategory(category))
            .fetch(); // 위에서 준비한 쿼리문을 DB 에 날려라
    }

    @Override
    public List<Product> getProductListWithPage(String category,long offset, int pageSize) {
        QProduct product = QProduct.product;

        return jpaQueryFactory.selectFrom(product)
            .where(eqCategory(category))
            .offset(offset)
            .limit(pageSize)
            .fetch();
    }

    @Override
    public List<Product> getProductListWithPageAndSortPriceDesc(String category, long offset, int pageSize) {
        QProduct product = QProduct.product;

        OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(Order.DESC, product.price);

        return jpaQueryFactory.selectFrom(product)
            .where(eqCategory(category))
            .orderBy(orderSpecifier)
            .offset(offset)
            .limit(pageSize)
            .fetch();
    }

    private BooleanExpression eqCategory(String category) {
        if (Objects.isNull(category)) {
            return null;
        }
        return product.category.eq(category);
    }
}

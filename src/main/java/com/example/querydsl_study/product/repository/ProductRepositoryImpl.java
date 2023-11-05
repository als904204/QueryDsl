package com.example.querydsl_study.product.repository;

import static com.example.querydsl_study.product.entity.QProduct.product;

import com.example.querydsl_study.product.dto.GetProductResponse;
import com.example.querydsl_study.product.dto.ProductSortByCondition;
import com.example.querydsl_study.product.entity.Product;
import com.example.querydsl_study.product.entity.QProduct;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * SELECT *
     * FROM product
     * WHERE product.category = '?'
     */
    @Override
    public List<Product> getProductListV1(String category) {
        QProduct product = QProduct.product;

        // 현재는 product 의 모든 컬럼을 조회한다
        return jpaQueryFactory.select(product)
            .from(product)
            .where(eqCategory(category))
            .fetch(); // 위에서 준비한 쿼리문을 DB 에 날려라
    }

    /**
     * SELECT *
     * FROM product
     * WHERE product.category = '?'
     */
    @Override
    public List<GetProductResponse> getProductListV2(String category) {
        QProduct product = QProduct.product;

        JPAQuery<Tuple> query = jpaQueryFactory.select(
                product.name,
                product.price,
                product.category
            )
            .from(product)
            .where(eqCategory(category));

        return query.fetch().stream()
            .map(tuple -> GetProductResponse.builder()
                .name(tuple.get(product.name))
                .price(tuple.get(product.price))
                .category(tuple.get(product.category))
                .build())
            .toList();

    }

    @Override
    public List<Product> getProductListV3WithPage(String category,long offset, int pageSize) {
        QProduct product = QProduct.product;

        return jpaQueryFactory.selectFrom(product)
            .where(eqCategory(category))
            .offset(offset)
            .limit(pageSize)
            .fetch();
    }

    @Override
    public List<Product> getProductListV4WithPageAndSortPriceDesc(String category, long offset, int pageSize) {
        QProduct product = QProduct.product;

        OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(Order.DESC, product.price);

        return jpaQueryFactory.selectFrom(product)
            .where(eqCategory(category))
            .orderBy(orderSpecifier)
            .offset(offset)
            .limit(pageSize)
            .fetch();
    }

    @Override
    public List<Product> getProductListV5WithPageAndSortByCondition(String category,
        ProductSortByCondition condition,
        long offset, int pageSize) {
        QProduct product = QProduct.product;

        OrderSpecifier<?>[] orderSpecifier = createOrderSpecifier(condition);
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

    private OrderSpecifier<?>[] createOrderSpecifier(ProductSortByCondition sortCondition) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

        if (Objects.isNull(sortCondition) || Objects.isNull(sortCondition.getSortBy())) {
            orderSpecifiers.add(QProduct.product.name.desc());
        } else if ("price".equalsIgnoreCase(sortCondition.getSortBy())) {
            orderSpecifiers.add(QProduct.product.price.desc());
        } else if ("id".equalsIgnoreCase(sortCondition.getSortBy())) {
            orderSpecifiers.add(QProduct.product.id.desc());
        }
        return orderSpecifiers.toArray(new OrderSpecifier[0]);
    }
}

package com.example.querydsl_study.order.repository;

import com.example.querydsl_study.order.dto.OrderListResponseDto;
import com.example.querydsl_study.order.entity.QOrder;
import com.example.querydsl_study.product.entity.QProduct;
import com.example.querydsl_study.user.entity.QUser;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    // 필요한 컬러만 조회
    @Override
    public List<OrderListResponseDto> getOrderListByUserIdV1(Long userId) {
        QUser user = QUser.user;
        QProduct product = QProduct.product;
        QOrder order = QOrder.order;

        /**
         * select o.id,
         *        u.name,
         *        u.address,
         *        p.id,
         *        p.name,
         *        p.price
         * from tb_order o
         *          join user u on o.user_id = u.id
         *          join product p on o.product_id = p.id
         *          where order.user_id = ?
         */
        JPAQuery<Tuple> query = queryFactory.select(
                order.id,
                user.name,
                user.address,
                product.id,
                product.name,
                product.price,
                product.category
            ).from(order)
            .join(user).on(order.user.id.eq(userId))
            .join(product).on(order.product.id.eq(product.id))
            .where(order.user.id.eq(userId));


        return query.fetch().stream()
            .map(tuple -> OrderListResponseDto.builder()
                .orderId(tuple.get(order.id))
                .userId(userId) // 파라매터에 이미 있는 값이므로 그대로 사용
                .userName(tuple.get(user.name))
                .userAddress(tuple.get(user.address))
                .productId(tuple.get(product.id))
                .productName(tuple.get(product.name))
                .productPrice(tuple.get(product.price))
                .productCategory(tuple.get(product.category))
                .build())
            .toList();
    }

    // 프로젝션 사용
    @Override
    public List<OrderListResponseDto> getOrderListByUserIdV2(Long userId) {
        QUser user = QUser.user;
        QProduct product = QProduct.product;
        QOrder order = QOrder.order;

        return queryFactory.select(
                Projections.constructor(OrderListResponseDto.class,
                    order.id,
                    user.id, // userId 추가
                    user.name,
                    user.address,
                    product.id,
                    product.name,
                    product.price,
                    product.category))
            .from(order)
            .join(user).on(order.user.id.eq(userId))
            .join(product).on(order.product.id.eq(product.id))
            .where(order.user.id.eq(userId))
            .fetch();
    }
}

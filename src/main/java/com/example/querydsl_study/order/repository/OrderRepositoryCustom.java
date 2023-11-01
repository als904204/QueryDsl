package com.example.querydsl_study.order.repository;

import com.example.querydsl_study.order.dto.OrderListResponseDto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoryCustom {

    List<OrderListResponseDto> getOrderListByUserIdV1(Long userId);

    List<OrderListResponseDto> getOrderListByUserIdV2(Long userId);

}

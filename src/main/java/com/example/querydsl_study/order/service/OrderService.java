package com.example.querydsl_study.order.service;

import com.example.querydsl_study.order.dto.OrderListResponseDto;
import com.example.querydsl_study.order.repository.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<OrderListResponseDto> getOrderListByUserIDV1(Long userId) {
        return orderRepository.getOrderListByUserIdV1(userId);
    }
    @Transactional(readOnly = true)
    public List<OrderListResponseDto> getOrderListByUserIDV2(Long userId) {
        return orderRepository.getOrderListByUserIdV2(userId);
    }

}

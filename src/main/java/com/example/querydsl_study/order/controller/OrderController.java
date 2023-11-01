package com.example.querydsl_study.order.controller;

import com.example.querydsl_study.order.dto.OrderListResponseDto;
import com.example.querydsl_study.order.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/api/v1/order/{id}")
    public ResponseEntity<List<OrderListResponseDto>> getOrderListByUserIdV1(@PathVariable Long id) {
        List<OrderListResponseDto> orderListByUserID = orderService.getOrderListByUserIDV1(id);
        return ResponseEntity.ok(orderListByUserID);
    }
    @GetMapping("/api/v2/order/{id}")
    public ResponseEntity<List<OrderListResponseDto>> getOrderListByUserIdV2(@PathVariable Long id) {
        List<OrderListResponseDto> orderListByUserID = orderService.getOrderListByUserIDV2(id);
        return ResponseEntity.ok(orderListByUserID);
    }
    @GetMapping("/api/v3/order/{id}")
    public ResponseEntity<List<OrderListResponseDto>> getOrderListByUserIdV3(@PathVariable Long id) {
        List<OrderListResponseDto> orderListByUserID = orderService.getOrderListByUserIDV2(id);
        return ResponseEntity.ok(orderListByUserID);
    }
}

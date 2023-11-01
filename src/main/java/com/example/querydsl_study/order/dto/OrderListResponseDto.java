package com.example.querydsl_study.order.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderListResponseDto {
    private Long orderId;
    private Long userId;
    private String userName;
    private String userAddress;
    private Long productId;
    private String productName;
    private Long productPrice;
    private String productCategory;

}

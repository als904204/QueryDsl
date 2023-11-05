package com.example.querydsl_study.order.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    final int repeatCount = 10;

    @DisplayName("DTO 로 직접 변환후 리턴 ")
    @Test
    public void testV1Repeated() {
        double totalAverageV1 = 0;
        for (int i = 0; i < repeatCount; i++) {
            double result = testV1Performance();
            totalAverageV1 += result;
        }
        System.out.println("V1 전체 평균 실행 시간: " + (totalAverageV1 / repeatCount) + "ms");
    }

    @DisplayName("Projections 사용")
    @Test
    public void testV2Repeated() {
        double totalAverageV2 = 0;
        for (int i = 0; i < repeatCount; i++) {
            double result = testV2Performance();
            totalAverageV2 += result;
        }
        System.out.println("V2 전체 평균 실행 시간: " + (totalAverageV2 / repeatCount) + "ms");
    }
    private double testV1Performance() {
        int testCount = 10;
        List<Long> timesV1 = new ArrayList<>();

        for (int i = 0; i < testCount; i++) {
            long startTimeV1 = System.currentTimeMillis();
            orderService.getOrderListByUserIDV1(1L);
            long endTimeV1 = System.currentTimeMillis();
            timesV1.add(endTimeV1 - startTimeV1);
        }

        long totalV1 = 0;
        for (Long time : timesV1) {
            totalV1 += time;
        }
        double averageTimeV1 = (double) totalV1 / testCount;

        System.out.println("V1 평균 실행 시간: " + averageTimeV1 + "ms");
        return averageTimeV1;
    }

    private double testV2Performance() {
        int testCount = 10;
        List<Long> timesV2 = new ArrayList<>();

        for (int i = 0; i < testCount; i++) {
            long startTimeV2 = System.currentTimeMillis();
            orderService.getOrderListByUserIDV2(1L);
            long endTimeV2 = System.currentTimeMillis();
            timesV2.add(endTimeV2 - startTimeV2);
        }

        long totalV2 = 0;
        for (Long time : timesV2) {
            totalV2 += time;
        }
        double averageTimeV2 = (double) totalV2 / testCount;

        System.out.println("V2 평균 실행 시간: " + averageTimeV2 + "ms");
        return averageTimeV2;
    }

}
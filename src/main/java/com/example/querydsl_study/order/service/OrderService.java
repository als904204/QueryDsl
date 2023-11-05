package com.example.querydsl_study.order.service;

import com.example.querydsl_study.order.dto.OrderListResponseDto;
import com.example.querydsl_study.order.entity.Order;
import com.example.querydsl_study.order.repository.OrderRepository;
import com.example.querydsl_study.product.entity.Product;
import com.example.querydsl_study.product.service.ProductService;
import com.example.querydsl_study.user.entity.User;
import com.example.querydsl_study.user.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;
    @Transactional(readOnly = true)
    public List<OrderListResponseDto> getOrderListByUserIDV1(Long userId) {
        return orderRepository.getOrderListByUserIdV1(userId);
    }
    @Transactional(readOnly = true)
    public List<OrderListResponseDto> getOrderListByUserIDV2(Long userId) {
        return orderRepository.getOrderListByUserIdV2(userId);
    }

//    @PostConstruct
    public void tempDatas() {

        User user = userService.findUserById(1L);
        Product product = productService.findProductById(1L);

        List<Order> ordersData = IntStream
            .range(0, 100000)
            .mapToObj(i -> {
                Order order = new Order();
                order.setUser(user);
                order.setProduct(product);
                return order;
            })
            .toList();
        orderRepository.saveAll(ordersData);
    }

//    @PostConstruct
    public void test1() {

        log.info("getOrderListByUserIDV1 실행");
        long startTimeV1 = System.currentTimeMillis();
        getOrderListByUserIDV1(1L);
        long endTimeV1 = System.currentTimeMillis();
        System.out.println("getOrderListByUserIDV1 Method Time: " + (endTimeV1 - startTimeV1) + "ms");
        log.info("getOrderListByUserIDV1 종료");

        System.out.println("==============");

        log.info("getOrderListByUserIDV2 실행");
        long startTimeV2 = System.currentTimeMillis();
        getOrderListByUserIDV2(1L);
        long endTimeV2 = System.currentTimeMillis();
        System.out.println("getOrderListByUserIDV2 Method Time: " + (endTimeV2 - startTimeV2) + "ms");
        log.info("getOrderListByUserIDV2 종료");

    }

//    @PostConstruct
    public void testV2() {
        log.info("V2테스트");
        // 테스트를 실행할 횟수
        int testCount = 10;
        List<Long> timesV2 = new ArrayList<>();

        for (int i = 0; i < testCount; i++) {
            // V2 테스트
            long startTimeV2 = System.currentTimeMillis();
            getOrderListByUserIDV2(1L);
            long endTimeV2 = System.currentTimeMillis();
            timesV2.add(endTimeV2 - startTimeV2);
        }

        /**
         * 평균 시간 계산 V2
         */
        long totalV2 = 0;
        for (Long time : timesV2) {
            totalV2 += time;
        }
        double averageTimeV2 = (double) totalV2 / testCount;


        log.info("V2 평균 실행 시간={}",averageTimeV2+"ms");
        log.info("V2 실행 시간={}",timesV2+"ms");
    }
//    @PostConstruct
    public void testV1() {
        log.info("V1테스트");

        // 테스트를 실행할 횟수
        int testCount = 10;
        List<Long> timesV1 = new ArrayList<>();

        for (int i = 0; i < testCount; i++) {
            // V1 테스트
            long startTimeV1 = System.currentTimeMillis();
            getOrderListByUserIDV1(1L);
            long endTimeV1 = System.currentTimeMillis();
            timesV1.add(endTimeV1 - startTimeV1);
        }

        /**
         * 평균 시간 계산 V1
         */
        long totalV1 = 0;
        for (Long time : timesV1) {
            totalV1 += time;
        }
        double averageTimeV1 = (double) totalV1 / testCount;

        log.info("V1 평균 실행 시간={}",averageTimeV1+"ms");
        log.info("V1 실행 시간={}",timesV1+"ms");
    }



}

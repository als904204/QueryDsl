package com.example.querydsl_study;

import com.example.querydsl_study.order.entity.Order;
import com.example.querydsl_study.order.repository.OrderRepository;
import com.example.querydsl_study.product.entity.Product;
import com.example.querydsl_study.product.repository.ProductRepository;
import com.example.querydsl_study.product.service.ProductService;
import com.example.querydsl_study.user.entity.User;
import com.example.querydsl_study.user.repository.UserRepository;
import com.example.querydsl_study.user.service.UserService;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempDatas {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    public void tempDatas() {
        User saveUser = User.builder()
            .name("user1")
            .email("email")
            .address("seoul")
            .build();
        userRepository.save(saveUser);

        Product saveProduct = Product.builder()
            .price(1000L)
            .category("category")
            .name("productA")
            .build();
        productRepository.save(saveProduct);

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

}

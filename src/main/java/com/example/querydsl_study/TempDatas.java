package com.example.querydsl_study;

import com.example.querydsl_study.order.entity.Order;
import com.example.querydsl_study.order.repository.OrderRepository;
import com.example.querydsl_study.product.entity.Product;
import com.example.querydsl_study.product.repository.ProductRepository;
import com.example.querydsl_study.product.service.ProductService;
import com.example.querydsl_study.user.entity.User;
import com.example.querydsl_study.user.repository.UserRepository;
import com.example.querydsl_study.user.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TempDatas {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

//    @PostConstruct
    public void testProductDatas() {
        log.info("testDats()");
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            products.add(new Product("Product " + i, 1L, "Category"));
        }
        log.info("products.size={}",products.size());

        productRepository.saveAll(products);
    }

//    @PostConstruct
    public void testOrderDatas() {
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

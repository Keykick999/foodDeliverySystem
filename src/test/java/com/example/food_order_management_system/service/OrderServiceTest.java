package com.example.food_order_management_system.service;

import com.example.food_order_management_system.entity.Menu;
import com.example.food_order_management_system.entity.Payment;
import com.example.food_order_management_system.enumerated.PaymentMethod;
import com.example.food_order_management_system.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
class OrderServiceTest {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    PayService payService;
    @Autowired
    PaymentRepository paymentRepository;

    @Test
    void order() {
    }
}








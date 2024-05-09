package com.example.food_order_management_system.service;


import com.example.food_order_management_system.entity.Order;
import com.example.food_order_management_system.entity.Payment;
import com.example.food_order_management_system.enumerated.OrderStatus;
import com.example.food_order_management_system.enumerated.PaymentMethod;
import com.example.food_order_management_system.exception.PaymentException;
import com.example.food_order_management_system.repository.OrderRepository;
import com.example.food_order_management_system.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PayService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;

    // 결제
    @Transactional
    public Payment pay(Long orderId, PaymentMethod paymentMethod) throws PaymentException {
        Optional<Order> findOrder = orderRepository.findById(orderId);
        if(findOrder.isPresent() && findOrder.get().getOrderState() != OrderStatus.CANCELLED) {
            Payment payment = new Payment(LocalDateTime.now(), paymentMethod, findOrder.get());
            paymentRepository.save(payment);
            return payment;
        } else {
          throw new PaymentException("Failed to process payment for order " + orderId);
        }
    }

    //회원 결제 내역 조회
    public List<Payment> findPaymentsByMemberId(String memberId) {
        return paymentRepository.findPaymentsByMemberId(memberId);
    }

    //orderId로 결제 내역 조회
    public List<Payment> findPaymentsByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }
}

package com.example.food_order_management_system.entity;

import com.example.food_order_management_system.enumerated.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
@Getter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @Column(name = "ORDER_TIME", nullable = false)
    private LocalDateTime orderTime;

    @Column(name = "ORDER_STATE", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderState; // 상태 관리를 위해 @Enumerated 사용 고려

    @Column(name = "TOTAL_PRICE", nullable = false)
    private int totalPrice;


    public Order(Member member, LocalDateTime orderTime, OrderStatus orderState, int totalPrice) {
        this.member = member;
        this.orderTime = orderTime;
        this.orderState = orderState;
        this.totalPrice = totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderState(OrderStatus orderState) {
        this.orderState = orderState;
    }
}
package com.example.food_order_management_system.entity;

import com.example.food_order_management_system.enumerated.PaymentMethod;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "PAYMENTS")
@NoArgsConstructor
@ToString
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PAYMENT_DATE", nullable = false)
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING) // 결제 수단을 Enum으로 관리
    @Column(name = "PAYMENT_METHOD", nullable = false)
    private PaymentMethod paymentMethod;

    @OneToOne // 주문과의 관계 설정
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    public Payment(LocalDateTime paymentDate, PaymentMethod paymentMethod, Order order) {
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.order = order;
    }
}

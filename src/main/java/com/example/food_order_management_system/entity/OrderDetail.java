package com.example.food_order_management_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "ORDER_DETAILS")
@Getter
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_DETAIL_ID")
    private Long orderDetailId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "MENU_ID", nullable = false)
    private Menu menu;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "PRICE", nullable = false)
    private int price;

    public OrderDetail(Order order, Menu menu, int quantity, int price) {
        this.order = order;
        this.menu = menu;
        this.quantity = quantity;
        this.price = price;
    }
}

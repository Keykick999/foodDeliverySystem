package com.example.food_order_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CATEGORIES")
@Getter
@NoArgsConstructor
@ToString
public class Category {         //그냥 enum으로 관리하는 게 낫나?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "CATEGORY_NAME", unique = true, nullable = false)
    private String categoryName;
}
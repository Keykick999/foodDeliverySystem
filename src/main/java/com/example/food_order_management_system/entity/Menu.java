package com.example.food_order_management_system.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Table(name = "MENUS")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Menu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long menuId;

    @Column(name = "MENU_NAME", nullable = false)
    private String menuName;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @Column(name = "PRICE", nullable = false)
    private int price;

    public Menu(String menuName, Category category, int price) {
        this.menuName = menuName;
        this.category = category;
        this.price = price;
    }
}
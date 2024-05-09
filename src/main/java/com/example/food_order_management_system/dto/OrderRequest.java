package com.example.food_order_management_system.dto;

import com.example.food_order_management_system.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private String memberId;
    private List<OrderItem> items;
}

package com.example.food_order_management_system.controller;

import com.example.food_order_management_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MyController {
    @Autowired
    private OrderService orderService;


    @GetMapping("/menus")
    public String showMenus() {
        return "menu";
    }


    @GetMapping("/menus/{menuId}")
    public String menuDetail(@PathVariable Long menuId, Model model) {
        model.addAttribute("menuId",menuId);
        return "menuDetail";
    }

    @GetMapping("/menus/cart")
    public String showCart() {
        return "cart";
    }

}

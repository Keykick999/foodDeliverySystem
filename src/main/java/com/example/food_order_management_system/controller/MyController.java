package com.example.food_order_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/menus")
    public String showMenus() {
        return "menu";
    }

    @GetMapping("/main")
    public String mainPage() {
        return "index";
    }
}

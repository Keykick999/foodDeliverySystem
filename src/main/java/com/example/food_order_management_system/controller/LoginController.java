package com.example.food_order_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/custom-login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login() {
        return "redirect:/"; // 로그인 후 리다이렉트할 페이지
    }
    
}

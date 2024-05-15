package com.example.food_order_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignInRequest {
    private String memberId;
    private String memberName;
    private String memberPassword;
    private String memberContact;
}

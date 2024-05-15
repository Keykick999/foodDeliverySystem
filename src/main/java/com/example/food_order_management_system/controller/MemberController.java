package com.example.food_order_management_system.controller;

import com.example.food_order_management_system.dto.LoginRequest;
import com.example.food_order_management_system.dto.SignInRequest;
import com.example.food_order_management_system.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    MemberService memberService;


    //회원 가입
    @PostMapping("/sign")
    public ResponseEntity<String> signIn(@RequestBody SignInRequest signInRequest) {
        String memberId = signInRequest.getMemberId();
        String memberName = signInRequest.getMemberName();
        String memberPassword = signInRequest.getMemberPassword();
        String memberContact = signInRequest.getMemberContact();
        boolean isRegistered = memberService.registerUser(memberId, memberName, memberPassword, memberContact);
        if (isRegistered) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to register user");
        }
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request, HttpSession session) {
        boolean authenticated = memberService.authenticate(request.getMemberId(), request.getMemberPassword());
        if(authenticated) {
            session.setAttribute("username", request.getMemberId());
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid userId or password");
        }
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("logout successful");
    }
}

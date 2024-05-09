//package com.example.food_order_management_system.controller;
//
//import com.example.food_order_management_system.config.JwtTokenUtil;
//import com.example.food_order_management_system.entity.Member;
//import com.example.food_order_management_system.repository.MemberRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//
//import java.util.Optional;
//
//@RestController
//public class AuthController {
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody Member member) {
//        if (member == null || member.getMemberId() == null || member.getMemberPassword() == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청");
//        }
//
//        // 사용자 ID로 조회하고 비밀번호를 확인
//        Optional<Member> findMember = memberRepository.findById(member.getMemberId());
//        if (findMember.isPresent() && BCrypt.checkpw(member.getMemberPassword(), findMember.get().getMemberPassword())) {
//            String token = jwtTokenUtil.generateToken(member.getMemberId());
//            return ResponseEntity.ok(token);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패");
//        }
//    }
//
//
////    @GetMapping("/sign")
////    public ResponseEntity<String> sign(@RequestBody )
//}

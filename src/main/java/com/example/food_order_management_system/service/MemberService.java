package com.example.food_order_management_system.service;

import com.example.food_order_management_system.entity.Member;
import com.example.food_order_management_system.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean registerUser(String memberId, String memberName, String password, String memberContact) {
        Optional<Member> findMember = memberRepository.findById(memberId);

        if (findMember.isPresent()) {
            Member existingMember = findMember.get();
            if (existingMember.getMemberContact().equals(memberContact)) {
                // 비밀번호 해싱
                String hashedPassword = passwordEncoder.encode(password);

                // 객체 생성
                Member member = new Member(memberId, memberName, hashedPassword, memberContact);

                // DB에 저장
                memberRepository.save(member);
                return true;
            }
        }
        return false;
    }

    public boolean authenticate(String memberId, String memberPassword) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if(findMember.isPresent()) {
            Member member = findMember.get();
            if(member.getMemberPassword().equals(memberPassword)) {
                return true;
            }
        }
        return false;
    }

}

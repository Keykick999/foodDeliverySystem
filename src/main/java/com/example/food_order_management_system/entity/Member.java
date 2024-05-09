package com.example.food_order_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "MEMBERS")
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "MEMBER_PASSWORD")
    private String memberPassword;

    @Column(name="CONTACT_NUMBER")
    private String contactNumber;

    public Member(String memberId, String memberPassword, String contactNumber) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.contactNumber = contactNumber;
    }
}
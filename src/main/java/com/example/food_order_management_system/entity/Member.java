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
@AllArgsConstructor
public class Member {
    @Id
    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "member_name",nullable = false)
    private String memberName;

    @Column(name = "MEMBER_PASSWORD",nullable = false)
    private String memberPassword;

    @Column(name="CONTACT_NUMBER", nullable = false, unique = true)
    private String memberContact;
}
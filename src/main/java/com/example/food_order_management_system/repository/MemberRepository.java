package com.example.food_order_management_system.repository;

import com.example.food_order_management_system.entity.Member;
import com.example.food_order_management_system.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member,String> {
    public boolean existsByMemberId(String memberId);

    //이름으로 회원 찾기
}

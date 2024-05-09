package com.example.food_order_management_system.repository;

import com.example.food_order_management_system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o WHERE o.member.memberId = :memberId")
    List<Order> findOrdersByMemberId(@Param("memberId") String memberId);
}

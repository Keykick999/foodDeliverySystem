package com.example.food_order_management_system.repository;

import com.example.food_order_management_system.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    @Query("SELECT p FROM Payment p JOIN p.order o WHERE o.member.memberId = :memberId")
    List<Payment> findPaymentsByMemberId(@Param("memberId") String memberId);

    @Query("SELECT p FROM Payment p WHERE order.orderId = :orderId")
    List<Payment> findByOrderId(@Param("orderId")Long orderId);
}

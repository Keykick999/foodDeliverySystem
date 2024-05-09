package com.example.food_order_management_system.repository;

import com.example.food_order_management_system.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    @Query("SELECT od FROM OrderDetail od JOIN od.menu m WHERE od.order.orderId = :orderId")
    List<OrderDetail> findOrderDetailsByOrderId(@Param("orderId") Long orderId);

    @Query("SELECT od FROM OrderDetail od join od.order o WHERE o.member.memberId = :memberId")
    List<OrderDetail> findOrderDetailsByMemberId(@Param("memberId") String memberId);
}

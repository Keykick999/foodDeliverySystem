package com.example.food_order_management_system.service;

import com.example.food_order_management_system.entity.*;
import com.example.food_order_management_system.enumerated.OrderStatus;
import com.example.food_order_management_system.enumerated.PaymentMethod;
import com.example.food_order_management_system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    MenuRepository menuRepository;



    //**회원 주문 내역 조회**//

    // 회원 주문 내역 조회
    public List<Order> findMemberOrderList(String memberId) {
        return orderRepository.findOrdersByMemberId(memberId);
    }


    // 회원 주문 상세 내역 조회
    public List<OrderDetail> findOrderDetailsByOrderId(Long orderId) {
        return orderDetailRepository.findOrderDetailsByOrderId(orderId);
    }


    // 주문
//    @Transactional
//    public void order(Map<Menu, Integer> menuList, String memberId) {
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(() -> new IllegalArgumentException("Member 정보가 유효하지 않습니다."));
//
//
//        Order order = new Order(member, LocalDateTime.now(), OrderStatus.ORDERED,0);
//        orderRepository.save(order);  // 주문 초기 저장
//
//        int totalPrice = 0;
//        for (Map.Entry<Menu, Integer> entry : menuList.entrySet()) {
//            Menu menu = entry.getKey();
//            Integer quantity = entry.getValue();
//            int price = menu.getPrice();
//
//            OrderDetail orderDetail = new OrderDetail(order, menu, quantity, price);
//            orderDetailRepository.save(orderDetail);  // 주문 상세 저장
//
//            totalPrice += price * quantity;
//        }
//
//        order.setTotalPrice(totalPrice);
//        orderRepository.save(order);  // 최종 totalPrice 업데이트
//    }

    //주문
    @Transactional
    public void order(Map<Long, Integer> menuIdsWithQuantity, String memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member 정보가 유효하지 않습니다."));

        Order order = new Order(member, LocalDateTime.now(), OrderStatus.ORDERED, 0);
        orderRepository.save(order);  // 주문 초기 저장

        int totalPrice = 0;
        for (Map.Entry<Long, Integer> entry : menuIdsWithQuantity.entrySet()) {
            Long menuId = entry.getKey();
            Integer quantity = entry.getValue();
            Menu menu = menuRepository.findById(menuId)
                    .orElseThrow(() -> new IllegalArgumentException("Menu ID가 유효하지 않습니다."));
            int price = menu.getPrice() * quantity;

            OrderDetail orderDetail = new OrderDetail(order, menu, quantity, price);
            orderDetailRepository.save(orderDetail);  // 주문 상세 저장

            totalPrice += price;
        }


        order.setTotalPrice(totalPrice);
        orderRepository.save(order);  // 최종 totalPrice 업데이트
    }




    // 주문 취소(전체 취소)
    @Transactional
    @CacheEvict(value = "orderDetails", key = "#orderId")
    public void cancelOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        //주문 상태 취소로 변경
        if(order.isPresent()) {
            Order orderInstance = order.get();
            orderInstance.setOrderState(OrderStatus.CANCELLED);
            orderRepository.save(orderInstance);
        }
    }






    //주문 취소(부분 취소는 일단 안되는 걸로!)
//    public void partialCancel(ArrayList<Long> orderDetailId) {  // n + 1 쿼리 문제?
//        Optional<OrderDetail> orderDetail;
//        for (Long id : orderDetailId) {
//            orderDetail = orderDetailRepository.findById(id);
//            //주문 상세 내역에 있으면 삭제
//            if(orderDetail.isPresent()) {
//                orderDetailRepository.delete(orderDetail.get());
//            }
//        }
//    }


}


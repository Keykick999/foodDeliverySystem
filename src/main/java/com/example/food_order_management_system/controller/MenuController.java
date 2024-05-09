package com.example.food_order_management_system.controller;


import com.example.food_order_management_system.dto.OrderItem;
import com.example.food_order_management_system.dto.OrderRequest;
import com.example.food_order_management_system.entity.Menu;
import com.example.food_order_management_system.entity.Order;
import com.example.food_order_management_system.entity.OrderDetail;
import com.example.food_order_management_system.entity.Payment;
import com.example.food_order_management_system.enumerated.PaymentMethod;
import com.example.food_order_management_system.exception.PaymentException;
import com.example.food_order_management_system.service.MenuService;
import com.example.food_order_management_system.service.OrderService;
import com.example.food_order_management_system.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private PayService payService;

    // 전체 메뉴 조회
    @GetMapping("/menus")
    public ResponseEntity<List<Menu>> findAllMenus() {
        List<Menu> menus = menuService.findAllMenus();
        if(menus.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(menus);
    }

    // 이름으로 메뉴 검색
    @GetMapping("/menus/search")
    public ResponseEntity<List<Menu>> searchMenusByName(@RequestParam String name) {
        List<Menu> menus = menuService.findMenusByName("%"+ name + "%");
        if(menus.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(menus);
    }

    // 카테고리별 메뉴 조회
    @GetMapping("/menus/categories")
    public ResponseEntity<List<Menu>> searchMenusByCategory(@RequestParam("categoryName") String categoryName) {
        List<Menu> menus = menuService.findMenuByCategoryName(categoryName);
        if(menus.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(menus);
    }

    // 회원 주문 내역 조회
    @GetMapping("/members/orders")
    public ResponseEntity<List<Order>> getOrdersByMember(@RequestParam("memberId") String memberId) {
        List<Order> orders = orderService.findMemberOrderList(memberId);
        if(orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }


    // 회원 주문 상세 내역 조회
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDetail>> getOrderDetails(@PathVariable("orderId") Long orderId) {
        List<OrderDetail> orderDetails = orderService.findOrderDetailsByOrderId(orderId);
        if(orderDetails.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orderDetails);
    }


    //주문 내역에 대한 결제
    @PostMapping("/members/pay")
    public ResponseEntity<List<Payment>> getPaymentsByMember(@RequestParam("orderId") Long orderId,
                                                             @RequestParam("paymentMethod")PaymentMethod paymentMethod) {
        try {
            payService.pay(orderId, paymentMethod);
            List<Payment> payments = payService.findPaymentsByOrderId(orderId);
            if (payments != null) {
                return ResponseEntity.ok(payments); // 성공 시 결제 내역 반환
            } else {
                return ResponseEntity.badRequest().body(Collections.emptyList());   // 주문이 취소되었거나 없는 경우(HTTP 상태 코드 400)
            }
        } catch (PaymentException e) {
            // 결제 처리 중에 예외 발생 시 (HTTP 상태 코드 500)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // 회원 결제 내역 조회
    @GetMapping("/members/payments")
    public ResponseEntity<List<Payment>> getPaymentsByMember(@RequestParam("memberId") String memberId) {
        List<Payment> payments = payService.findPaymentsByMemberId(memberId);
        if(payments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(payments);
    }


    // 주문
    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        Map<Long, Integer> orderItems = orderRequest.getItems()
                .stream()
                .collect(Collectors.toMap(OrderItem::getMenuId, OrderItem::getQuantity));
        orderService.order(orderItems, orderRequest.getMemberId());
        return ResponseEntity.ok().build(); // 요청이 성공적으로 처리되었다는 응답을 반환
    }



    //주문 취소
    @PostMapping("/orders/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok().build();
    }

}

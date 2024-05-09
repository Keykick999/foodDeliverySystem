package com.example.food_order_management_system.enumerated;

public enum OrderStatus {
    ORDERED,    //주문 접수는 되었지만 처리는 X
    PREPARING,  //음식 준비 중
    READY,      //음식 준비 완료
    CANCELLED   //주문 취소
}

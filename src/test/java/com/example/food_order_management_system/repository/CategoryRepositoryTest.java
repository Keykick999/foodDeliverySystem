package com.example.food_order_management_system.repository;

import static org.assertj.core.api.Assertions.assertThat;


import com.example.food_order_management_system.entity.*;
import com.example.food_order_management_system.enumerated.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;



    @Test
    public void testFindAllCategories() {
        Member member = new Member("brian2","dddd","010-0900-0000");
        memberRepository.save(member);
        Order order = new Order(member, LocalDateTime.now(), OrderStatus.ORDERED,30000);
        System.out.println("order.getOrderId() = " + order.getOrderId());
        orderRepository.save(order);
        System.out.println("order.getOrderId() = " + order.getOrderId());
    }
//
//    @Test
//    public void testFindCategoryById() {
//        Category category = new Category("Test Category");
//        categoryRepository.save(category);
//
//        Optional<Category> optionalCategory = categoryRepository.findById(category.getCategoryId());
//        assertThat(optionalCategory).isPresent();
//        assertThat(optionalCategory.get().getCategoryName()).isEqualTo(category.getCategoryName());
//    }
//
//    @Test
//    public void testDeleteCategory() {
//        Category category = new Category("Test Category");
//        categoryRepository.save(category);
//        categoryRepository.delete(category);
//
//        assertThat(categoryRepository.findById(category.getCategoryId())).isEmpty();
//    }
//
//    @Test
//    public void testCategoryNameCannotBeNull() {
////        Category category = new Category();
////        assertThrows(DataIntegrityViolationException.class, () -> {
////            categoryRepository.save(category);
////        });
//    }
}

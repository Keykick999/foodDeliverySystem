package com.example.food_order_management_system.repository;

import com.example.food_order_management_system.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    //전체 메뉴 조회
    @Query("SELECT m FROM Menu m")
    List<Menu> findAllMenus();

    //이름 으로 메뉴 조회
    @Query("SELECT m FROM Menu m WHERE m.menuName LIKE :name")
    List<Menu> findMenusByName(@Param("name")String name);

    //카테고리명으로 메뉴 조회
    @Query("SELECT m FROM Menu m JOIN m.category c WHERE c.categoryName = :categoryName")
    List<Menu> findMenusByCategoryName(@Param("categoryName") String categoryName);
}

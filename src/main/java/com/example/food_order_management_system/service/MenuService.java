package com.example.food_order_management_system.service;

import com.example.food_order_management_system.entity.Menu;
import com.example.food_order_management_system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;


    //**메뉴 조회**//


    // 전체 메뉴 조회
    @Cacheable("menus")
    public List<Menu> findAllMenus() {
//        return menuRepository.findAllMenus();
        return menuRepository.findAll();
    }


    //id로 조회
    @Cacheable(value = "menu", key = "#menuId")
    public Optional<Menu> findById(Long menuId) {return menuRepository.findById(menuId);}

    // 비슷한 이름 으로 메뉴 조회
    public List<Menu> findMenusByName(String menuName) {
        return menuRepository.findMenusByName("%" + menuName + "%");
    }


    // 카테고리명으로 메뉴 조회
    public List<Menu> findMenuByCategoryName(String categoryName) {
        return menuRepository.findMenusByCategoryName(categoryName);
    }

}

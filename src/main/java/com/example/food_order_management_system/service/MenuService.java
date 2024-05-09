package com.example.food_order_management_system.service;

import com.example.food_order_management_system.entity.Menu;
import com.example.food_order_management_system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;


    //**메뉴 조회**//

    // 전체 메뉴 조회
    public List<Menu> findAllMenus() {
        return menuRepository.findAllMenus();
    }


    // 비슷한 이름 으로 메뉴 조회
    public List<Menu> findMenusByName(String menuName) {
        return menuRepository.findMenusByName("%" + menuName + "%");
    }


    // 카테고리명으로 메뉴 조회
    public List<Menu> findMenuByCategoryName(String categoryName) {
        return menuRepository.findMenusByCategoryName(categoryName);
    }

}

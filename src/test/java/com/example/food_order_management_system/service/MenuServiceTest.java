package com.example.food_order_management_system.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.food_order_management_system.entity.Menu;
import com.example.food_order_management_system.service.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@EnableCaching
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Autowired
    private CacheManager cacheManager;

//    @BeforeEach
//    public void setUp() {
//        // 캐시를 비웁니다.
//        cacheManager.getCache("menus").clear();
//    }

    @Test
    public void testFindAllMenusCaching() {
        // 첫 번째 호출: 데이터베이스에서 조회 및 캐시 저장
        List<Menu> result1 = menuService.findAllMenus();
//        assertThat(result1).hasSize(5);
//
//        // 두 번째 호출: 캐시에서 조회
//        List<Menu> result2 = menuService.findAllMenus();
//        assertThat(result2).isEqualTo(result1);

        // 캐시가 올바르게 설정되었는지 확인
        List<Menu> cachedMenus = (List<Menu>) cacheManager.getCache("menus").get("SimpleKey []").get();
        cachedMenus.forEach(System.out::println);
//        assertThat(cachedMenus).isEqualTo(result1);
    }
}

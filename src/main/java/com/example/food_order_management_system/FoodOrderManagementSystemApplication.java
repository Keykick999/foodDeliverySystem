package com.example.food_order_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FoodOrderManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodOrderManagementSystemApplication.class, args);
	}

}

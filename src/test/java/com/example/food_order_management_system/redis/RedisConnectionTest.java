package com.example.food_order_management_system.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@DataRedisTest
public class RedisConnectionTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void testRedisConnection() {
        // Redis에 데이터 저장
        redisTemplate.opsForValue().set("testKey", "testValue");

        // Redis에서 데이터 가져오기
        String rightValue = redisTemplate.opsForValue().get("testKey");
        String wrongValue = redisTemplate.opsForValue().get("key2");

        // 검증
        System.out.println("value = " + rightValue);
        System.out.println("wrongValue = " + wrongValue);
        assertThat("testValue").isEqualTo(rightValue);
        assertThat("testValue").isNotEqualTo(wrongValue);
    }
}

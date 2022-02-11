package com.sparta.homework001.food.repository;

import com.sparta.homework001.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByRestaurantId(Long restaurantId);
    List<Food> findAllByRestaurantIdAndName(Long restaurant, String name);
}

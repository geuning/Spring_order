package com.sparta.homework001.food.controller;

import com.sparta.homework001.food.dto.FoodRequestDto;
import com.sparta.homework001.food.dto.FoodResponseDto;
import com.sparta.homework001.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    //음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public ResponseEntity registerFood(@RequestBody List<FoodRequestDto> requestDtos, @PathVariable Long restaurantId){
        foodService.registerFood(requestDtos, restaurantId);
        return ResponseEntity.ok().build();
    }

    //메뉴판 조회 response 사용
    @GetMapping("/restaurant/{restaurantId}/foods")
    public ResponseEntity<List<FoodResponseDto>> getFoods(@PathVariable Long restaurantId){
        return ResponseEntity.ok(foodService.getFoods(restaurantId));
    }

    //메뉴판 조회
//    @GetMapping("/restaurant/{restaurantId}/foods")
//    public List<FoodResponseDto> getFoods(@PathVariable Long restaurantId){
//        List<Food> foods = foodService.getFoods(restaurantId);
//        List<FoodResponseDto> foodDtos = foods.stream().map(food -> new FoodResponseDto(food.getName(), food.getId(), food.getPrice()))
//                .collect(Collectors.toList());
//        return foodDtos;
//    }
}

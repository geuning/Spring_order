package com.sparta.homework001.restaurant.controller;

import com.sparta.homework001.restaurant.dto.RestaurantRequestDto;
import com.sparta.homework001.restaurant.dto.RestaurantResponseDto;
import com.sparta.homework001.restaurant.entity.Restaurant;
import com.sparta.homework001.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

//    ㅡㅡ@RequiredArgsConstructor 없을때ㅡㅡ
//    public RestaurantController(RestaurantService restaurantService) {
//        this.restaurantService = restaurantService;
//    }

    //음식점 등록
    @PostMapping("/restaurant/register")
    public Restaurant registerRestaurant(@RequestBody RestaurantRequestDto requestDto){
        return restaurantService.registerRestaurant(requestDto);
    }
//    //음식점 조회 model사용
//    @GetMapping("/restaurants")
//    public List<Restaurant> getRestaurants(){
//        return restaurantService.getRestaurants();
//    }

    //음식점 조회 responseDto사용
    @GetMapping("/restaurants")
    public List<RestaurantResponseDto> getRestaurants(){
        return restaurantService.getRestaurants();
    }

}

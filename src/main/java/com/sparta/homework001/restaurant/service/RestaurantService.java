package com.sparta.homework001.restaurant.service;


import com.sparta.homework001.restaurant.dto.RestaurantRequestDto;
import com.sparta.homework001.restaurant.dto.RestaurantResponseDto;
import com.sparta.homework001.restaurant.entity.Restaurant;
import com.sparta.homework001.food.dto.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional

    //음식점 등록
    public Restaurant registerRestaurant(RestaurantRequestDto requestDto){
        int minOrderPrice = requestDto.getMinOrderPrice();
        int deliveryFee = requestDto.getDeliveryFee();

        if (minOrderPrice < 1000 || minOrderPrice > 100000) {
            throw new IllegalArgumentException("주문 가격은 1,000원 이상 100,000원 이하로 입력해주세요.");
        } else if (minOrderPrice % 100 !=0) {
            throw new IllegalArgumentException("최소 입력단위는 100원 입니다.");
        } else if (deliveryFee < 0 || deliveryFee > 10000) {
            throw new IllegalArgumentException("배달비는 0원 이상 10,000원 이하 입니다.");
        } else if (deliveryFee % 500 !=0) {
            throw new IllegalArgumentException("배달비는 500원 단위로 입력해주세요");
        }

        Restaurant restaurant = new Restaurant(requestDto);
        restaurantRepository.save(restaurant);

        return restaurant;
    }
//    //음식점 조회 model사용
//    public List<Restaurant> getRestaurants(){
//        return restaurantRepository.findAll();
//    }


    //음식점 조회 responseDto사용
    public List<RestaurantResponseDto> getRestaurants(){
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        List<RestaurantResponseDto> restaurantResponseDtos = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto();
            restaurantResponseDto.setId(restaurant.getId());
            restaurantResponseDto.setName(restaurant.getName());
            restaurantResponseDto.setDeliveryFee(restaurant.getDeliveryFee());
            restaurantResponseDto.setMinOrderPrice(restaurant.getMinOrderPrice());
            restaurantResponseDtos.add(restaurantResponseDto);
        }
        return restaurantResponseDtos;
    }

}

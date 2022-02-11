package com.sparta.homework001.food.service;


import com.sparta.homework001.food.dto.FoodRequestDto;
import com.sparta.homework001.food.dto.FoodResponseDto;
import com.sparta.homework001.food.entity.Food;
import com.sparta.homework001.restaurant.entity.Restaurant;
import com.sparta.homework001.food.repository.FoodRepository;
import com.sparta.homework001.food.dto.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    //음식 등록
    public void registerFood(List<FoodRequestDto> requestDtos, Long restaurantId){

        for (FoodRequestDto requestDto : requestDtos) {
            String name = requestDto.getName();
            int price = requestDto.getPrice();

            if (price < 100 || price > 1000000) {
                throw new IllegalArgumentException("음식 가격은 100원 이상 1,000,000원 이하로 입력해주세요");
            } else if (price % 100 != 0) {
                throw new IllegalArgumentException("음식 가격은 100원 단위로 입력해주세요.");
            } else if (!foodRepository.findAllByRestaurantIdAndName(restaurantId, name).isEmpty()){
                throw new IllegalArgumentException("같은 이름의 음식은 추가할 수 없습니다.");
            }

            Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                    () -> new IllegalArgumentException("식당이 존재하지 않습니다.")
            );

            Food food = new Food(name, price, restaurant);
            foodRepository.save(food);
        }
    }

//    //메뉴판 조회
//    public List<Food> getFoods(Long restaurantId){
//        return foodRepository.findAllByRestaurantId(restaurantId);
//    }

    //메뉴판 조회 response사용
    public List<FoodResponseDto> getFoods(Long restaurantId) {
        List<Food> foodList = foodRepository.findAllByRestaurantId(restaurantId);
        List<FoodResponseDto> foodResponseDtos = new ArrayList<>();
        for (Food food : foodList) {
            FoodResponseDto foodResponseDto = new FoodResponseDto();
            foodResponseDto.setId(food.getId());
            foodResponseDto.setName(food.getName());
            foodResponseDto.setPrice(food.getPrice());
            foodResponseDtos.add(foodResponseDto);
        }
        return foodResponseDtos;
    }
}

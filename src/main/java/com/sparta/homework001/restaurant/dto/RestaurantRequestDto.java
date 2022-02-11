package com.sparta.homework001.restaurant.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantRequestDto {
    private String name;
    private int minOrderPrice;
    private int deliveryFee;

}

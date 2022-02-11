package com.sparta.homework001.food.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FoodRequestDto {
    private String name;
    private int price;
}

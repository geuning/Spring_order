package com.sparta.homework001.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FoodResponseDto {
    Long id;
    private String name;
    private int price;
}

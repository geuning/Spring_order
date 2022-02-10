package com.sparta.homework001.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private Long restaurantId;
    private List<OrderItemRequestDto> foods;
}

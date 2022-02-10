package com.sparta.homework001.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private String restaurantName;
    private List<OrderItemRequestDto> foods;
    private int deliveryFee;
    private int totalPrice;

}

package com.sparta.homework001.orders.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private String restaurantName;
    private List<OrderItemResponseDto> foods;
    private int deliveryFee;
    private int totalPrice;

}

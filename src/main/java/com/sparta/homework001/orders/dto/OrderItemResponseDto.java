package com.sparta.homework001.orders.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponseDto {
    private String name;
    private int price;
    private int quantity;
}

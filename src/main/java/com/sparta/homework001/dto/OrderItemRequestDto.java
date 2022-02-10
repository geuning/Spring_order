package com.sparta.homework001.dto;

import lombok.*;

@Getter
@Setter
public class OrderItemRequestDto {
    private Long id;
//    private String name;
//    private int price;
    private int quantity;

//    @Builder
//    public OrderItemRequestDto(String name, int price, int quantity){
//        this.name = name;
//        this.price = price;
//        this.quantity = quantity;
//    }

}

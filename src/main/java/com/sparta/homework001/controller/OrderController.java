package com.sparta.homework001.controller;


import com.sparta.homework001.dto.FoodResponseDto;
import com.sparta.homework001.dto.OrderItemRequestDto;
import com.sparta.homework001.dto.OrderRequestDto;
import com.sparta.homework001.dto.RestaurantResponseDto;
import com.sparta.homework001.model.Orders;
import com.sparta.homework001.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //주문
    @PostMapping("/order/request")
    public Orders registerOrders(@RequestBody OrderRequestDto requestDto){
        Long restaurantId = requestDto.getRestaurantId();
        List<OrderItemRequestDto> orderListDto = requestDto.getFoods();
        return orderService.registerOrders(restaurantId, orderListDto);
    }

    //주문 조회
    @GetMapping("/orders")
    public List<Orders> getOrders(){
        return orderService.getOrders();
    }

}

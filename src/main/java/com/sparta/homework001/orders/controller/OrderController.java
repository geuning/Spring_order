package com.sparta.homework001.orders.controller;


import com.sparta.homework001.orders.dto.OrderItemRequestDto;
import com.sparta.homework001.orders.dto.OrderRequestDto;
import com.sparta.homework001.orders.dto.OrderResponseDto;
import com.sparta.homework001.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    //주문
    @PostMapping("/order/request")
    public OrderResponseDto registerOrders(@RequestBody OrderRequestDto requestDto){
        Long restaurantId = requestDto.getRestaurantId();
        List<OrderItemRequestDto> orderListDto = requestDto.getFoods();

        for (OrderItemRequestDto orderItemRequestDto : orderListDto) {
            if(orderItemRequestDto.getQuantity() < 1 || orderItemRequestDto.getQuantity() > 100) {
                throw new IllegalArgumentException("음식 주문 수량을 확인해주세요");
            }
        }

        return orderService.registerOrders(restaurantId, orderListDto);
    }

    //주문 조회
    @GetMapping("/orders")
    public List<OrderResponseDto> getOrders(){
        return orderService.getOrders();
    }

}

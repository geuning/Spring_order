package com.sparta.homework001.service;

import com.sparta.homework001.dto.*;
import com.sparta.homework001.model.Food;
import com.sparta.homework001.model.OrderItem;
import com.sparta.homework001.model.Orders;
import com.sparta.homework001.model.Restaurant;
import com.sparta.homework001.repository.OrderItemRepository;
import com.sparta.homework001.repository.OrderRepository;
import com.sparta.homework001.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderItemRepository orderItemRepository;


    public Orders registerOrders(Long restaurantId, List<OrderItemRequestDto> orderItemRequestDtos){

    }

    //주문조회
    public List<Orders> getOrders(){
        return orderRepository.findAll();
    }


//    //주문조회
//    public OrderResponseDto getOrders(){
//        OrderResponseDto orderResponseDtos = new OrderResponseDto();
//        orderResponseDtos.setRestaurantName(객체.getRestaurantName());
//        List<OrderItem> foods = orderItemRepository.findAll();
//        for(Orders orders : foods){
//            OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
//            orderItemResponseDto.setName(orders.getName());
//            orderItemResponseDto.setQuantity(orders.getQuantity());
//            orderItemResponseDto.setPrice(orders.getPrice());
//        }
//    }

//    public Orders orderfood(OrderRequestDto requestDto) {
//    }
}

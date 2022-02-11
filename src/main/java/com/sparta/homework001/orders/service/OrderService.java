package com.sparta.homework001.orders.service;

import com.sparta.homework001.food.entity.Food;
import com.sparta.homework001.orders.dto.OrderItemRequestDto;
import com.sparta.homework001.orders.dto.OrderItemResponseDto;
import com.sparta.homework001.orders.dto.OrderResponseDto;
import com.sparta.homework001.orders.entity.OrderItem;
import com.sparta.homework001.orders.entity.Orders;
import com.sparta.homework001.restaurant.entity.Restaurant;
import com.sparta.homework001.food.repository.FoodRepository;
import com.sparta.homework001.orders.repository.OrderItemRepository;
import com.sparta.homework001.orders.repository.OrderRepository;
import com.sparta.homework001.food.dto.repository.RestaurantRepository;
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
    private final FoodRepository foodRepository;


    public OrderResponseDto registerOrders(Long restaurantId, List<OrderItemRequestDto> orderItemRequestDtos){
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("식당이 존재하지 않습니다."));

        // 1. 주문정보 저장
        Orders orders = Orders.createOrder();
        for (OrderItemRequestDto orderItemRequestDto : orderItemRequestDtos) {
            Food food = foodRepository.findById(orderItemRequestDto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("음식이 존재하지 않습니다."));
            OrderItem orderItem = new OrderItem(orderItemRequestDto.getQuantity(),food.getPrice(), food, orders);
        }

        // 주문 최소 가격 체크
        int orderTotalFee = orders.getOrderTotalPrice(restaurant.getDeliveryFee());
        if(orderTotalFee < restaurant.getMinOrderPrice()) {
            throw new RuntimeException("음식점 주문 최소 가격 미만 주문시 주문이 안됩니다.");
        }

        orderRepository.save(orders);

        // 2. response 값 만들기
        List<OrderItemResponseDto> foods = new ArrayList<>();
        List<OrderItem> orderItems = orders.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
            orderItemResponseDto.setPrice(orderItem.getPrice() * orderItem.getQuantity());
            orderItemResponseDto.setQuantity(orderItem.getQuantity());
            orderItemResponseDto.setName(orderItem.getFood().getName());
            foods.add(orderItemResponseDto);
        }

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setTotalPrice(orderTotalFee);
        orderResponseDto.setRestaurantName(restaurant.getName());
        orderResponseDto.setDeliveryFee(restaurant.getDeliveryFee());
        orderResponseDto.setFoods(foods);

        return orderResponseDto;
    }

    //주문조회
    public List<OrderResponseDto> getOrders(){
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();

        List<Orders> orders = orderRepository.findAll();
        for (Orders order : orders) {

            OrderResponseDto orderResponseDto = new OrderResponseDto();
            List<OrderItemResponseDto> foods = new ArrayList<>();
            List<OrderItem> orderItems = order.getOrderItems();

            Restaurant restaurant = order.getOrderItems().get(0).getFood().getRestaurant();
            orderResponseDto.setRestaurantName(restaurant.getName());
            orderResponseDto.setDeliveryFee(restaurant.getDeliveryFee());

            for (OrderItem orderItem : orderItems) {

                OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
                orderItemResponseDto.setName(orderItem.getFood().getName());
                orderItemResponseDto.setQuantity(orderItem.getQuantity());
                orderItemResponseDto.setPrice(orderItem.getPrice() * orderItem.getQuantity());

                foods.add(orderItemResponseDto);
            }
            orderResponseDto.setFoods(foods);
            orderResponseDto.setTotalPrice(order.getOrderTotalPrice(restaurant.getDeliveryFee()));

            orderResponseDtos.add(orderResponseDto);
        }

        return orderResponseDtos;
    }

}

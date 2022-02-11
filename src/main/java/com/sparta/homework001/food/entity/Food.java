package com.sparta.homework001.food.entity;

import com.sparta.homework001.restaurant.entity.Restaurant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Food {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

//    @Column(nullable = false)
//    private Long restaurantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Food(String name, int price, Restaurant restaurant){
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

//    public Food(FoodRequestDto requestDto, Long restaurantId){
//        this.name = requestDto.getName();
//        this.price = requestDto.getPrice();
//        this.restaurantId = restaurantId;
//    }
}

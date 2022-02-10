package com.sparta.homework001.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @OneToMany
    private List<OrderItem> foods;

    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private int totalPrice;

    public Orders(String restaurantName, List<OrderItem> foods, int deliveryFee, int totalPrice){
        this.restaurantName = restaurantName;
        this.foods = foods;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
    }

//    private Date orderDate;

}

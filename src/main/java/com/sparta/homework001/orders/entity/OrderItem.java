package com.sparta.homework001.orders.entity;

import com.sparta.homework001.food.entity.Food;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orders;

    public OrderItem(int quantity, int price, Food food, Orders orders){
        this.quantity = quantity;
        this.price = price;
        this.food = food;
        this.orders = orders;
        orders.addOrderItem(this);
    }

}

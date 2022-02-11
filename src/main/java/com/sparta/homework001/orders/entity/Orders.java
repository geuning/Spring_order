package com.sparta.homework001.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItems = new ArrayList<>();

    private String status;

    private Date orderDate;

    public static Orders createOrder() {
        return Orders.builder()
                .orderDate(new Date())
                .status("ORDER")
                .orderItems(new ArrayList<>())
                .build();
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public int getOrderTotalPrice(int deliveryFee) {
        int totalPrice = 0;
        totalPrice += deliveryFee;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getPrice() * orderItem.getQuantity();
        }
        return totalPrice;
    }

}

package com.sparta.homework001.orders.repository;

import com.sparta.homework001.orders.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}

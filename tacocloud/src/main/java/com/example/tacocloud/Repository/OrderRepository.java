package com.example.tacocloud.Repository;

import com.example.tacocloud.Order;

public interface OrderRepository {
    Order save(Order order);
}

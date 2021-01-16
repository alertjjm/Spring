package com.example.tacocloud.Repository;

import com.example.tacocloud.Model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}

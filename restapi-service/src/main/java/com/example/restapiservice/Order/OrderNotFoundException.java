package com.example.restapiservice.Order;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Could not find order "+id);
    }
}

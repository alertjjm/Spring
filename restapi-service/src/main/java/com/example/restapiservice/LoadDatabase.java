package com.example.restapiservice;

import com.example.restapiservice.Employee.Employee;
import com.example.restapiservice.Employee.EmployeeRepository;
import com.example.restapiservice.Order.Order;
import com.example.restapiservice.Order.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
@Configuration
@Slf4j
public class LoadDatabase implements CommandLineRunner{
    @Autowired
    EmployeeRepository repository;
    @Autowired
    OrderRepository orderRepository;
    @Override
    public void run(String... args) throws Exception {
        log.info("Preloading " + repository.save(new Employee("Bilbo","Baggins", "burglar")));
        log.info("Preloading " + repository.save(new Employee("Frodo","Baggins", "thief")));
        log.info("Preloading "+orderRepository.save(new Order("MacBook Pro", Status.COMPLETED)));
        log.info("Preloading "+orderRepository.save(new Order("iPhone", Status.IN_PROGRESS)));
    }
}

package com.example.ingredientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import com.netflix.discovery.EurekaClient;
@EnableDiscoveryClient
@SpringBootApplication
public class IngredientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IngredientServiceApplication.class, args);
    }

}

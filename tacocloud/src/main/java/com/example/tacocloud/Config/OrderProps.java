package com.example.tacocloud.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "taco.orders")
@Data
public class OrderProps {
    private int pageSize=20;
}

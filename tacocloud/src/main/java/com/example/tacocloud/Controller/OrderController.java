package com.example.tacocloud.Controller;

import com.example.tacocloud.Model.Order;
import com.example.tacocloud.Repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepo;
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus){
        if(errors.hasErrors()){
            return "orderForm";
        }
        log.info("Order submitted: "+order);
        orderRepo.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }
}

package com.example.tacocloud.Controller;

import com.example.tacocloud.Model.Order;
import com.example.tacocloud.Model.User;
import com.example.tacocloud.Repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
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
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user){
        if(errors.hasErrors()){
            return "orderForm";
        }
        order.setUser(user);
        log.info("Order submitted: "+order);
        orderRepo.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user, @ModelAttribute Order order){
        if(order.getDeliveryName()==null)
            order.setDeliveryName(user.getFullname());
        if(order.getDeliveryStreet()==null)
            order.setDeliveryStreet(user.getStreet());
        if(order.getDeliveryCity()==null)
            order.setDeliveryCity(user.getCity());
        if(order.getDeliveryState()==null)
            order.setDeliveryState(user.getState());
        if(order.getDeliveryZip()==null)
            order.setDeliveryZip(user.getZip());
        return "orderForm";
    }
}

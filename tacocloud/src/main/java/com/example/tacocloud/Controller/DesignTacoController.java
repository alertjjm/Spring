package com.example.tacocloud.Controller;

import com.example.tacocloud.Model.Ingredient;
import com.example.tacocloud.Model.Order;
import com.example.tacocloud.Model.User;
import com.example.tacocloud.Repository.IngredientRepository;
import com.example.tacocloud.Repository.TacoRepository;
import com.example.tacocloud.Model.Taco;
import com.example.tacocloud.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.example.tacocloud.Model.Ingredient.Type;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    @Autowired
    private IngredientRepository ingredientRepo;
    @Autowired
    private TacoRepository tacoRepo;
    @Autowired
    private UserRepository userRepo;
    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }
    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }
    @GetMapping
    public String showDesignForm(Model model, @AuthenticationPrincipal User user){
        List<Ingredient> ingredients=new ArrayList<>();
        ingredientRepo.findAll().forEach(x->ingredients.add(x));
        Type[] types=Ingredient.Type.values();
        for(Type type:types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients,type));
        }
        //model.addAttribute("taco",new Taco());
        //String username = principal.getName();
        //User user = userRepo.findByUsername(username);
        model.addAttribute("user", user);
        return "design";
    }
    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order){
        if(errors.hasErrors()){
            return "design";
        }
        log.info("Processing design: "+design);
        Taco saved=tacoRepo.save(design);
        order.addDesign(saved);
        return "redirect:/orders/current";
    }
    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        return ingredients.stream().filter(x->x.getType().equals(type)).collect(Collectors.toList());
    }
}

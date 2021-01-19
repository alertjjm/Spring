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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path="/design", produces="application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {
    @Autowired
    private TacoRepository tacoRepo;
    @Autowired
    EntityLinks entityLinks;
    @GetMapping("/recent")
    public Iterable<Taco> recentTacos(){
        PageRequest page=PageRequest.of(0,12, Sort.by("createdAt").descending());
        return tacoRepo.findAll(page).getContent();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id){
        Optional<Taco> opttTaco=tacoRepo.findById(id);
        if(opttTaco.isPresent()){
            return new ResponseEntity<>(opttTaco.get(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        return tacoRepo.save(taco);
    }
}

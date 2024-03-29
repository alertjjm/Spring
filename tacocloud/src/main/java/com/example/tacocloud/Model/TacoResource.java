package com.example.tacocloud.Model;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;

@Getter
public class TacoResource extends RepresentationModel<TacoResource>{
    private final String name;
    private final Date createdAt;
    private final List<Ingredient> ingredients;
    public TacoResource(Taco taco){
        this.name=taco.getName();
        this.createdAt=taco.getCreatedAt();
        this.ingredients=taco.getIngredients();
    }
}

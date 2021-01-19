package com.example.tacocloud.Model;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import com.example.tacocloud.Model.Ingredient.Type;
@Getter
public class IngredientResource extends RepresentationModel<IngredientResource> {
    private String name;
    private Type type;
    public IngredientResource(Ingredient ingredient){
        this.name=ingredient.getName();
        this.type=ingredient.getType();
    }
}

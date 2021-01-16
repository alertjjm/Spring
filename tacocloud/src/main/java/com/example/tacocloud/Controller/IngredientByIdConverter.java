package com.example.tacocloud.Controller;

import com.example.tacocloud.Ingredient;
import com.example.tacocloud.Repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    @Autowired
    IngredientRepository ingredientRepo;
    @Override
    public Ingredient convert(String source) {
        Optional<Ingredient> optionalIngredient=ingredientRepo.findById(source);
        return optionalIngredient.isPresent()?optionalIngredient.get():null;
    }
}

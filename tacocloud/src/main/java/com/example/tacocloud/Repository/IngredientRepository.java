package com.example.tacocloud.Repository;

import com.example.tacocloud.Model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}

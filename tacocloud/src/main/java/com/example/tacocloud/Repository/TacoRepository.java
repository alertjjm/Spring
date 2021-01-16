package com.example.tacocloud.Repository;

import com.example.tacocloud.Model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}

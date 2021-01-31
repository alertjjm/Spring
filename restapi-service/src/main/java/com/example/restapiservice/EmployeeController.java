package com.example.restapiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private EmployeeModelAssembler assembler;
    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> all() {
        List<EntityModel<Employee>>  employees=repository.findAll().stream()
                .map(assembler::toModel)    //오 신기하다. map에서 메소드 매핑만시켜줘도되는구나! java8 특징인가봐
                .collect(Collectors.toList());
        return CollectionModel.of(employees,linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }
    @PostMapping("/employees")
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        Employee resultEmployee=repository.save(newEmployee);
        System.out.println(repository.findAll());
        return resultEmployee;
    }

    // Single item

    @GetMapping("/employees/{id}")
    public EntityModel<Employee> one(@PathVariable Long id) {
        Employee employee= repository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
        return assembler.toModel(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        Employee employee;
        Optional<Employee> optionalEmployee=repository.findById(id);
        if(optionalEmployee.isPresent()){
            employee=optionalEmployee.get();
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return repository.save(employee);
        }
        else{
            newEmployee.setId(id);
            return repository.save(newEmployee);
        }
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

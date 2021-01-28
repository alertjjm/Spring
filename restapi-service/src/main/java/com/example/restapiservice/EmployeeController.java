package com.example.restapiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;
    @GetMapping("/employees")
    public ResponseEntity all() {
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }
    @PostMapping("/employees")
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    // Single item

    @GetMapping("/employees/{id}")
    public Employee one(@PathVariable Long id) {
        Optional<Employee> employee=repository.findById(id);
        return employee.isPresent()?employee.get():null;
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

package com.example.restapiservice.Employee;

public class EmployeeNotFoundException extends RuntimeException{
    EmployeeNotFoundException(Long id){
        super("Could not find employee "+id);
    }
}

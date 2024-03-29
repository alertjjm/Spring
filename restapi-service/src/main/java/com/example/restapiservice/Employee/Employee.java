package com.example.restapiservice.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String role;

    public Employee(String firstName,String lastName, String role) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.role=role;
    }
    //REFATOR
    public String getName(){
        return this.firstName+" "+this.lastName;
    }
    //REFATOR
    public void setName(String name){
        String[] parts=name.split(" ");
        this.firstName=parts[0];
        this.lastName=parts[1];
    }
}

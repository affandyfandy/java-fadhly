package com.example.assignment2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
public class Employee implements Serializable  {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
    private Date dateofbirth;
    private String address;
    private String department;

    public Employee() {
    }
    public Employee(String id, String name, Date dateOfBirth, String address, String department) {
        this.id = id;
        this.name = name;
        this.dateofbirth = dateOfBirth;
        this.address = address;
        this.department = department;
    }
}
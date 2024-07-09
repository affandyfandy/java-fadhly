package example.lecture7.assignment1.model;

import example.lecture7.assignment1.controller.EmployeeWork;

import org.springframework.beans.factory.annotation.Autowired;

public class Employee {
    private String name;
    private EmployeeWork employeeWork;

    @Autowired
    public Employee(String name, EmployeeWork employeeWork) {
        this.name = name;
        this.employeeWork = employeeWork;
    }

    public void working() {
        System.out.println("My name is: " + name + " - Using Constructor Injection");
        employeeWork.work();
    }
}
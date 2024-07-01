package example.lecture7.assignment1.model;

import example.lecture7.assignment1.controller.EmployeeWork;

import org.springframework.beans.factory.annotation.Autowired;

public class Employee2 {
    private String name;

    @Autowired
    private EmployeeWork employeeWork;

    public Employee2() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void working() {
        System.out.println("My name is: " + name);
        employeeWork.work();
    }
}

package example.lecture7.assignment1.model;

import example.lecture7.assignment1.controller.EmployeeWork;

import org.springframework.beans.factory.annotation.Autowired;

public class Employee3 {
    private String name;
    private EmployeeWork employeeWork;

    public Employee3() {}

    public void setName(String name) {
        this.name = name;
    }

    @Autowired
    public void setEmployeeWWork(EmployeeWork employeeWork) {
        this.employeeWork = employeeWork;
    }

    public void working() {
        System.out.println("My name is: " + name+ " - Using Setter Injection");
        employeeWork.work();
    }
}

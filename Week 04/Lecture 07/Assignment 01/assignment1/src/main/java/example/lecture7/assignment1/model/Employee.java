package example.lecture7.assignment1.model;

import example.lecture7.assignment1.controller.EmployeeWork;

public class Employee {
    private String name;
    private EmployeeWork employeeWork;

    public Employee(String name, EmployeeWork employeeWork) {
        this.name = name;
        this.employeeWork = employeeWork;
    }

    public void working() {
        System.out.println("My name is: " + name);
        employeeWork.work();
    }
}
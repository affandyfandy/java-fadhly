package example.lecture8.assignment3.model;

import lombok.Data;

@Data
public class Employee {
    private int id;
    private String name;
    private String department;
    private int age;

    public String show() {
        return "Id: " + id + "\nName: " + name + "\nDepartment: " + department + "\nAge: " + age;
    }
}

package example.lecture9.assignment3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="employee")
@Getter
@Setter
public class Employee {
    @Id
    private String id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateofbirth;
    private String address;
    private String department;
    private int salary;

    public Employee() {}

    public Employee(String id, String name, Date dateOfBirth, String address, String department, int salary) {
        this.id = id;
        this.name = name;
        this.dateofbirth = dateOfBirth;
        this.address = address;
        this.department = department;
        this.salary = salary;
    }
}
